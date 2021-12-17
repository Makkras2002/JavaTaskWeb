package com.makkras.shop.controller.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.UserService;
import com.makkras.shop.controller.CustomCommand;
import com.makkras.shop.controller.util.MessageManager;
import com.makkras.shop.controller.util.PathManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfRegCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = String.valueOf(request.getSession().getAttribute(Literal.LOGIN_NAME));
        String password = String.valueOf(request.getSession().getAttribute(Literal.PASSWORD));
        String email = String.valueOf(request.getSession().getAttribute(Literal.EMAIL));
        if(login != null && password != null && email != null){
            try {
                if(UserService.getInstance().checkIfUserIsValidForRegistration(login,email)){
                    UserService.getInstance().registerUser(login,password,email);
                    page = PathManager.getInstance().getProperty("path.page.mainCl");
                } else {
                    page = PathManager.getInstance().getProperty("path.page.auth");
                    request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE, MessageManager.getInstance().getProperty("message.confRegErrorV2"));
                }
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            try {
                page = PathManager.getInstance().getProperty("path.page.auth");
                request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE, MessageManager.getInstance().getProperty("message.confRegError"));
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        }
        return page;
    }
}
