package com.makkras.shop.test_main;

import com.makkras.shop.dao.UserDao;
import com.makkras.shop.dao.impl.UserDaoImpl;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        try {
            List<User> userList = userDao.findAllUsersWithRole(UserRole.ADMIN);
            logger.info(userList);
        } catch (InteractionException e) {
            logger.error(e.getMessage());
        }
    }
}
