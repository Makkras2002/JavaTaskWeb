package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        String page = null;
        String login = request.getParameter(Literal.LOGIN_NAME);
        String password = request.getParameter(Literal.PASSWORD);
        Boolean enterAsAdmin = true;
        if(request.getParameter(Literal.ENTER_AS_ADMIN) ==null){
            enterAsAdmin =false;
        }
        try {
            if(request.getSession().getAttribute(Literal.LOGIN_NAME) == null){
                Optional<User> foundUser = UserService.getInstance().findUserWithLoginAndPassword(login,password,enterAsAdmin);
                if(foundUser.isPresent()){
                    request.getSession().setAttribute(Literal.LOGIN_NAME, foundUser.get().getLogin());
                    request.getSession().setAttribute(Literal.PASSWORD, foundUser.get().getPassword());
                    request.getSession().setAttribute(Literal.EMAIL, foundUser.get().getEmail());
                    if(enterAsAdmin){
                        request.getSession().setAttribute(Literal.ROLE, UserRole.ADMIN);
                    } else {
                        request.getSession().setAttribute(Literal.ROLE, UserRole.CLIENT);
                    }
                    request.getSession().setAttribute(Literal.ORDER, null);
                    page = Literal.MAIN_CLIENT_PAGE;
                }else {
                    request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,"LOGIN_ERROR"));
                    page = Literal.AUTHORIZATION_PAGE;
                }
            } else {
                request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"FIRST_LOGOUT_ERROR"));
                page = Literal.AUTHORIZATION_PAGE;
            }

        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}