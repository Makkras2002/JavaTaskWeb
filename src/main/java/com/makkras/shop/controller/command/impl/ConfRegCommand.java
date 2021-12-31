package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfRegCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        String page = null;
        String login = String.valueOf(request.getSession().getAttribute(Literal.LOGIN_NAME));
        String password = String.valueOf(request.getSession().getAttribute(Literal.PASSWORD));
        String email = String.valueOf(request.getSession().getAttribute(Literal.EMAIL));
        if(login != null && password != null && email != null){
            try {
                if(UserService.getInstance().checkIfUserIsValidForRegistration(login,email)){
                    UserService.getInstance().registerUser(login,password,email);
                    page = PagePath.MAIN_CLIENT_PAGE;
                } else {
                    page = PagePath.AUTHORIZATION_PAGE;
                    request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,
                                    "CONFIRMATION_REGISTRATION_ERROR_V2"));
                }
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            page = PagePath.AUTHORIZATION_PAGE;
            request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                    localizedTextExtractor.getText(currentLocale ,"CONFIRMATION_REGISTRATION_ERROR"));
        }
        return page;
    }
}
