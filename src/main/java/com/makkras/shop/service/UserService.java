package com.makkras.shop.service;

import com.makkras.shop.dao.UserDao;
import com.makkras.shop.dao.impl.UserDaoImpl;
import com.makkras.shop.encryptor.impl.PasswordEncryptor;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserService {
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
        Optional<User> result= null;
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
    public void setUserStatusNotOnlineInDb(String login) throws ServiceException {
        try {
            userDao.updateOnlineStatus(login,false);
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
