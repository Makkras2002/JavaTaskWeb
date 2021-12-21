package com.makkras.shop.service;

import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;

import java.util.Optional;

public interface CustomUserService {
    Optional<User> findUserWithLoginAndPassword(String login, String password, Boolean enterAsAdmin) throws ServiceException;
    boolean checkIfUserIsValidForRegistration(String login, String email) throws ServiceException;
    boolean registerUser(String login,String password, String email) throws ServiceException;
    void sendMessageAboutSuccessFullRegistrationOnUserEmail(String login, String email);
    void setUserStatusNotOnlineInDb(String login) throws ServiceException;
}
