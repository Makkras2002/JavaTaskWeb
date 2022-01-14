package com.makkras.shop.service;

import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Custom user service.
 */
public interface CustomUserService {
    /**
     * Find user with login and password and return optional result.
     *
     * @param login        the login
     * @param password     the password
     * @param enterAsAdmin the enter as admin
     * @return the optional result
     * @throws ServiceException the service exception
     */
    Optional<User> findUserWithLoginAndPassword(String login, String password, Boolean enterAsAdmin) throws ServiceException;

    /**
     * Check if user is valid for registration and return boolean result if user valid.
     *
     * @param login the login
     * @param email the email
     * @return the boolean check result
     * @throws ServiceException the service exception
     */
    boolean checkIfUserIsValidForRegistration(String login, String email) throws ServiceException;

    /**
     * Register user and return boolean result if user was successfully registered.
     *
     * @param login    the login
     * @param password the password
     * @param email    the email
     * @return the boolean success result
     * @throws ServiceException the service exception
     */
    boolean registerUser(String login,String password, String email) throws ServiceException;

    /**
     * Send message about successful registration on user email.
     *
     * @param login         the login
     * @param email         the email
     * @param currentLocale the current locale, chosen by current user
     */
    void sendMessageAboutSuccessFullRegistrationOnUserEmail(String login, String email, String currentLocale);

    /**
     * Sets user status not online in database.
     *
     * @param login the login
     * @throws ServiceException the service exception
     */
    void setUserStatusNotOnlineInDb(String login) throws ServiceException;

    /**
     * Sets user new login.
     *
     * @param login    the old login
     * @param newLogin the new login
     * @throws ServiceException the service exception
     */
    void setUserNewLogin(String login,String newLogin) throws ServiceException;

    /**
     * Sets user new password.
     *
     * @param login       the login
     * @param newPassword the new password
     * @throws ServiceException the service exception
     */
    void setUserNewPassword(String login,String newPassword) throws ServiceException;

    /**
     * Find active offline user with login and return optional result.
     *
     * @param login the login
     * @return the optional result
     * @throws ServiceException the service exception
     */
    Optional<User> findUserWithLogin(String login) throws ServiceException;

    /**
     * Find user with login from all users and return optional result.
     *
     * @param login the login
     * @return the optional result
     * @throws ServiceException the service exception
     */
    Optional<User> findUserWithLoginFromAllUsers(String login) throws ServiceException;

    /**
     * Find all users and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Register administrator and return boolean result if registration was successful.
     *
     * @param login    the login
     * @param password the password
     * @param email    the email
     * @return the boolean success result
     * @throws ServiceException the service exception
     */
    boolean registerAdmin(String login,String password, String email) throws ServiceException;

    /**
     * Find active users from database by parameters and collect them in the list.
     *
     * @param login  the login
     * @param email  the email
     * @param role   the role
     * @param status the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findActiveUsersFromDbByParams(String login,String email,String role,Boolean status) throws ServiceException;

    /**
     * Delete user and return boolean result if deletion was successful.
     *
     * @param login the login
     * @return the boolean success result
     * @throws ServiceException the service exception
     */
    boolean deleteUser(String login) throws ServiceException;
}
