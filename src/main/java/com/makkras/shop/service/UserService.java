package com.makkras.shop.service;

import com.makkras.shop.dao.UserDao;
import com.makkras.shop.dao.impl.UserDaoImpl;
import com.makkras.shop.encryptor.impl.PasswordEncryptor;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.util.mail.MailSender;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    private static final String SUCCESSFUL_REG_EMAIL_HEADER = "Web-Shop \"AutoShop\"";
    private static final String SUCCESSFUL_REG_EMAIL_BODY = """
            <html><body><p>Congratulations on successful registration on Web-Shop \"AutoShop\". Please, follow the link to confirm registration. </p></br> 
            <a href=\" http://localhost:8888/pages/confreg.jsp\">Confirm registration</a></body></html>""";
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
    public void sendMessageAboutSuccessFullRegistrationOnUserEmail(String login, String email){
        String finalRegistrationMessage = login+", "+SUCCESSFUL_REG_EMAIL_BODY;
        MailSender.getInstance().send(email,SUCCESSFUL_REG_EMAIL_HEADER,finalRegistrationMessage);
    }
    public void setUserStatusNotOnlineInDb(String login) throws ServiceException {
        try {
            userDao.updateOnlineStatus(login,false);
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
