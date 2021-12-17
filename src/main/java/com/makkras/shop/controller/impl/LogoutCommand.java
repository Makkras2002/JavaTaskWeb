package com.makkras.shop.controller.impl;

import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.UserService;
import com.makkras.shop.controller.CustomCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page ="path.page.auth";
        try {
            UserService.getInstance().setUserStatusNotOnlineInDb(String.valueOf(request.getSession().getAttribute("login")));
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        request.getSession().invalidate();
        return page;
    }
}
