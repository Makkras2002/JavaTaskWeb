package com.makkras.shop.dao.impl;

import com.makkras.shop.dao.UserDao;
import com.makkras.shop.encryptor.impl.PasswordEncryptor;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
    private static Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_ALL_USERS = "SELECT users.user_id,users.login,users.password," +
            "users.email,user_roles.role_name,users.is_active,users.is_online FROM users " +
            "JOIN user_roles ON users.role_id =user_roles.role_id";
    private static final String SQL_SELECT_ALL_ONLINE_USERS = "SELECT users.user_id,users.login,users.password," +
            "users.email,user_roles.role_name,users.is_active,users.is_online FROM users " +
            "JOIN user_roles ON users.role_id =user_roles.role_id WHERE is_online = true";
    private static final String SQL_SELECT_ALL_ACTIVE_USERS = "SELECT users.user_id,users.login,users.password," +
            "users.email,user_roles.role_name,users.is_active,users.is_online FROM users " +
            "JOIN user_roles ON users.role_id =user_roles.role_id WHERE is_active = true";
    private static final String SQL_SELECT_ALL_USERS_WITH_ROLE = "SELECT users.user_id,users.login,users.password," +
            "users.email,user_roles.role_name,users.is_active,users.is_online FROM users " +
            "JOIN user_roles ON users.role_id =user_roles.role_id WHERE user_roles.role_name = ?";
    private static final String SQL_SELECT_USER_WITH_EMAIL = "SELECT users.user_id,users.login,users.password," +
            "users.email,user_roles.role_name,users.is_active,users.is_online FROM users " +
            "JOIN user_roles ON users.role_id =user_roles.role_id WHERE users.email = ?";
    private static final String SQL_SELECT_USER_WITH_LOGIN = "SELECT users.user_id,users.login,users.password," +
            "users.email,user_roles.role_name,users.is_active,users.is_online FROM users " +
            "JOIN user_roles ON users.role_id =user_roles.role_id WHERE users.login = ?";
    private static final String SQL_CREATE_USER= "INSERT INTO users (login,password," +
            "email,is_active,is_online,role_id) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER_LOGIN= "UPDATE users SET login  = ? WHERE login = ?";
    private static final String SQL_UPDATE_USER_ACTIVATION_STATUS= "UPDATE users SET is_active  = ? WHERE login = ?";
    private static final String SQL_UPDATE_USER_ONLINE_STATUS= "UPDATE users SET is_online  = ? WHERE login = ?";
    private static final String SQL_UPDATE_USER_PASSWORD= "UPDATE users SET password  = ? WHERE login = ? AND password = ?";


    public UserDaoImpl(){
    }
    @Override
    public List<User> findAll() throws InteractionException{
        List<User> users = selectDataFromDbByQuery(SQL_SELECT_ALL_USERS);
        return users;
    }

    @Override
    public Long create(User user) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        Long createdItemKey = null;
        try {
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_USER,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2,passwordEncryptor.encryptPassword(user.getPassword()));
            statement.setString(3, user.getEmail());
            statement.setBoolean(4, user.isActive());
            statement.setBoolean(5, user.isOnline());
            statement.setInt(6,user.getUserRoleId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                createdItemKey = resultSet.getLong(1);
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return createdItemKey;
    }

    @Override
    public List<User> findAllActiveUsers() throws InteractionException {
        List<User> users = selectDataFromDbByQuery(SQL_SELECT_ALL_ACTIVE_USERS);
        return users;
    }

    @Override
    public List<User> findAllOnlineUsers() throws InteractionException {
        List<User> users = selectDataFromDbByQuery(SQL_SELECT_ALL_ONLINE_USERS);
        return users;
    }

    @Override
    public List<User> findAllUsersWithRole(UserRole role) throws InteractionException{
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS_WITH_ROLE);
            if(role.equals(UserRole.ADMIN)){
                statement.setString(1,"admin");
            }else {
                statement.setString(1,"client");
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long userId = resultSet.getLong(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                UserRole role1;
                if(resultSet.getString(5).equals("admin")){
                    role1 = UserRole.ADMIN;
                }else {
                    role1 = UserRole.CLIENT;
                }
                boolean isActive = resultSet.getBoolean(6);
                boolean isOnline = resultSet.getBoolean(7);
                users.add(new User(userId,login,email,password,role1,isActive,isOnline));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return users;
    }


    @Override
    public List<User> findUserWithSuchEmail(String email) throws InteractionException{
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER_WITH_EMAIL);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long userId = resultSet.getLong(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email1 = resultSet.getString(4);
                UserRole role;
                if(resultSet.getString(5).equals("admin")){
                    role = UserRole.ADMIN;
                }else {
                    role = UserRole.CLIENT;
                }
                boolean isActive = resultSet.getBoolean(6);
                boolean isOnline = resultSet.getBoolean(7);
                users.add(new User(userId,login,email1,password,role,isActive,isOnline));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return users;
    }
    @Override
    public List<User> findUserWithSuchLogin(String login) throws InteractionException{
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER_WITH_LOGIN);
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long userId = resultSet.getLong(1);
                String login1 = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                UserRole role;
                if(resultSet.getString(5).equals("admin")){
                    role = UserRole.ADMIN;
                }else {
                    role = UserRole.CLIENT;
                }
                boolean isActive = resultSet.getBoolean(6);
                boolean isOnline = resultSet.getBoolean(7);
                users.add(new User(userId,login1,email,password,role,isActive,isOnline));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return users;
    }

    @Override
    public boolean updateLogin(String currentLogin, String newLogin) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER_LOGIN);
            statement.setString(1,newLogin);
            statement.setString(2,currentLogin);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updateActivationStatus(String login, boolean newActivationStatus) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER_ACTIVATION_STATUS);
            statement.setBoolean(1,newActivationStatus);
            statement.setString(2,login);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updateOnlineStatus(String login, boolean newIsOnlineStatus) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER_ONLINE_STATUS);
            statement.setBoolean(1,newIsOnlineStatus);
            statement.setString(2,login);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updatePassword(String login, String oldPassword, String newPassword) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD);
            statement.setString(1, passwordEncryptor.encryptPassword(newPassword));
            statement.setString(2,login);
            statement.setString(3, passwordEncryptor.encryptPassword(oldPassword));
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        } catch (InteractionException e) {
            logger.error(e.getMessage());
            return false;
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }
    private List<User> selectDataFromDbByQuery(String query) throws InteractionException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long userId = resultSet.getLong(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);
                UserRole role;
                if(resultSet.getString(5).equals("admin")){
                    role = UserRole.ADMIN;
                }else {
                    role = UserRole.CLIENT;
                }
                boolean isActive = resultSet.getBoolean(6);
                boolean isOnline = resultSet.getBoolean(7);
                users.add(new User(userId,login,email,password,role,isActive,isOnline));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return users;
    }

}
