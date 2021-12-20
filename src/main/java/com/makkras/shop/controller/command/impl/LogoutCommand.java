package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.UserService;
import com.makkras.shop.controller.command.CustomCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            UserService.getInstance().setUserStatusNotOnlineInDb(String.valueOf(request.getSession().getAttribute("login")));
            page = Literal.AUTHORIZATION_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        request.getSession().invalidate();
        return page;
    }
}
