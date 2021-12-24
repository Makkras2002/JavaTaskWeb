package com.makkras.shop.service.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.dao.UserDao;
import com.makkras.shop.dao.impl.UserDaoImpl;
import com.makkras.shop.controller.util.encryptor.impl.PasswordEncryptor;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.CustomUserService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import com.makkras.shop.util.mail.MailSender;

import java.util.List;
import java.util.Optional;

public class UserService implements CustomUserService {
    private static UserService instance;
    private UserDao userDao;
    private PasswordEncryptor encryptor;
    private UserService(){
        userDao = new UserDaoImpl();
        encryptor = new PasswordEncryptor();
    }
    public static UserService getInstance(){
        if(instance ==null){
            instance = new UserService();
        }
        return instance;
    }
    public Optional<User> findUserWithLoginAndPassword(String login,String password,Boolean enterAsAdmin) throws ServiceException {
        String encryptedPassword = encryptor.encryptPassword(password);
        Optional<User> result = Optional.empty();
        try {
            List<User> foundUsers = userDao.findUserWithSuchLogin(login);
            if(foundUsers.size() == 0){
                return result;
            }else {
                if(foundUsers.get(0).getPassword().equals(encryptedPassword)){
                    if(!enterAsAdmin){
                        result = Optional.of(foundUsers.get(0));
                        userDao.updateOnlineStatus(login,true);
                        return result;
                    }else {
                        if(foundUsers.get(0).getUserRoleName().equals(UserRole.ADMIN.toString())){
                            result = Optional.of(foundUsers.get(0));
                            userDao.updateOnlineStatus(login,true);
                            return result;
                        }else {
                            return result;
                        }
                    }
                } else {
                    return result;
                }
            }
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public boolean checkIfUserIsValidForRegistration(String login, String email) throws ServiceException {
        try {
            List<User> foundUsers = userDao.findUserFromAllUsersWithSuchLogin(login);
            if(foundUsers.size() != 0){
                return false;
            }else {
                foundUsers = userDao.findUserWithSuchEmail(email);
                if(foundUsers.size() != 0){
                    return false;
                } else {
                    return true;
                }
            }
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public boolean registerUser(String login,String password, String email) throws ServiceException {
        User userForRegistration = new User(login, email, password,UserRole.CLIENT,true,true);
        try {
            Optional<Long> idOfCreatedUser = Optional.ofNullable(userDao.create(userForRegistration));
            if(idOfCreatedUser.isPresent()){
                return true;
            }else {
                return false;
            }
        } catch (InteractionException e){
            throw new ServiceException(e.getMessage());
        }

    }
    public void sendMessageAboutSuccessFullRegistrationOnUserEmail(String login, String email,String currentLocale){
        String finalRegistrationMessage = login+", "+ LocalizedTextExtractor.getInstance().getText(currentLocale, "SUCCESSFUL_REG_EMAIL_BODY");
        MailSender.getInstance().send(email,Literal.SUCCESSFUL_REG_EMAIL_HEADER,finalRegistrationMessage);
    }
    public void setUserStatusNotOnlineInDb(String login) throws ServiceException {
        try {
            userDao.updateOnlineStatus(login,false);
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}