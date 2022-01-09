package com.makkras.shop.service;

import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CustomUserService {
    Optional<User> findUserWithLoginAndPassword(String login, String password, Boolean enterAsAdmin) throws ServiceException;
    boolean checkIfUserIsValidForRegistration(String login, String email) throws ServiceException;
    boolean registerUser(String login,String password, String email) throws ServiceException;
    void sendMessageAboutSuccessFullRegistrationOnUserEmail(String login, String email, String currentLocale);
    void setUserStatusNotOnlineInDb(String login) throws ServiceException;
    void setUserNewLogin(String login,String newLogin) throws ServiceException;
    void setUserNewPassword(String login,String newPassword) throws ServiceException;
    Optional<User> findUserWithLogin(String login) throws ServiceException;
    Optional<User> findUserWithLoginFromAllUsers(String login) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    boolean registerAdmin(String login,String password, String email) throws ServiceException;
    List<User> findActiveUsersFromDbByParams(String login,String email,String role,Boolean status) throws ServiceException;
    boolean deleteUser(String login) throws ServiceException;
}
