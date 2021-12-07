package com.makkras.shop.dao;

import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;

import java.util.List;

public interface UserDao extends BaseDao<User>{
    List<User> findAllActiveUsers() throws InteractionException;
    List<User> findAllOnlineUsers() throws InteractionException;
    List<User> findAllUsersWithRole(UserRole role) throws InteractionException;
    List<User> findUserWithSuchEmail(String email) throws InteractionException;
    List<User> findUserWithSuchLogin(String login) throws InteractionException;
    boolean updateLogin(String currentLogin, String newLogin) throws InteractionException;
    boolean updateActivationStatus(String login,boolean newActivationStatus) throws InteractionException;
    boolean updateOnlineStatus(String login,boolean newIsOnlineStatus) throws InteractionException;
    boolean updatePassword(String login,String oldPassword,String newPassword) throws InteractionException;
}
