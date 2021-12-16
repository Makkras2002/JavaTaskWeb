package com.makkras.shop.servlet.impl;

import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.UserService;
import com.makkras.shop.servlet.CustomCommand;
import com.makkras.shop.servlet.util.MessageManager;
import com.makkras.shop.servlet.util.PathManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String REG_LOGIN = "login";
    private static final String REG_EMAIL = "email";
    private static final String REG_PASSWORD = "password";
    private static final String DEFAULT_LOCALE = "RU";
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(REG_LOGIN);
        String email = request.getParameter(REG_EMAIL);
        String password = request.getParameter(REG_PASSWORD);
        String page = null;
        try {
            if(UserService.getInstance().checkIfUserIsValidForRegistration(login,email)){
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute("password", password);
                request.getSession().setAttribute("email", email);
                request.getSession().setAttribute("locale", DEFAULT_LOCALE);
                request.getSession().setAttribute("order", null);
                UserService.getInstance().sendMessageAboutSuccessFullRegistrationOnUserEmail(login,email);
                request.setAttribute("errorAuthMessage", MessageManager.getInstance().getProperty("message.registrationConfirmAwait"));
                page = PathManager.getInstance().getProperty("path.page.auth");
            } else {
                request.setAttribute("errorRegistrationMessage", MessageManager.getInstance().getProperty("message.registrationError"));
                page = PathManager.getInstance().getProperty("path.page.reg");
            }
        } catch (ServiceException e){
            logger.error(e.getMessage());
        }
        return page;
    }
}
