package com.makkras.shop.controller.listener;

import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class CustomSessionListener implements HttpSessionListener {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        try {
            UserService.getInstance().setUserStatusNotOnlineInDb(String.valueOf(se.getSession().getAttribute("login")));
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
    }
}
