package com.makkras.shop.dao;

import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;

import java.util.List;

public interface UserDao extends BaseDao<User>{

    /**
     * Find all active users and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<User> findAllActiveUsers() throws InteractionException;
    /**
     * Find all online users and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<User> findAllOnlineUsers() throws InteractionException;

    /**
     * Find all users with role and collect them in the list.
     *
     * @param role the role
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<User> findAllUsersWithRole(UserRole role) throws InteractionException;

    /**
     * Find user with such email and collect them in the list.
     *
     * @param email the email
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<User> findUserWithSuchEmail(String email) throws InteractionException;

    /**
     * Find active offline user with such login and collect them in the list.
     *
     * @param login the login
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<User> findUserWithSuchLogin(String login) throws InteractionException;

    /**
     * Find user from all users with such login and collect them in the list.
     *
     * @param login the login
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<User> findUserFromAllUsersWithSuchLogin(String login) throws InteractionException;

    /**
     * Update login and return boolean true if operation was successful.
     *
     * @param currentLogin the current login
     * @param newLogin     the new login
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateLogin(String currentLogin, String newLogin) throws InteractionException;

    /**
     * Update activation status and return boolean true if operation was successful.
     *
     * @param login               the login
     * @param newActivationStatus the new activation status
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateActivationStatus(String login,boolean newActivationStatus) throws InteractionException;

    /**
     * Update online status and return boolean true if operation was successful.
     *
     * @param login             the login
     * @param newIsOnlineStatus the new is online status
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateOnlineStatus(String login,boolean newIsOnlineStatus) throws InteractionException;

    /**
     * Update password and return boolean true if operation was successful.
     *
     * @param login       the login
     * @param newPassword the new password
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updatePassword(String login, String newPassword) throws InteractionException;
}
