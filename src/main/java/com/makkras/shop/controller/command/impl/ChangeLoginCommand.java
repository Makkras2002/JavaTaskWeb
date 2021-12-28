package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PagePath;
import com.makkras.shop.controller.validator.impl.CustomUserDataValidator;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ChangeLoginCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login  = request.getParameter(Literal.LOGIN_NAME);
        UserService userService = UserService.getInstance();
        HttpSession session = request.getSession();
        String currentLocale = String.valueOf(request.getSession().getAttribute(Literal.LOCALE_NAME));
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        if(CustomUserDataValidator.getInstance().validateUserChangeLoginData(login)){
            try {
                Optional<User> foundUser = userService.findUserWithLogin(login);
                if(!foundUser.isPresent()){
                    String oldLogin = session.getAttribute(Literal.LOGIN_NAME).toString();
                    session.setAttribute(Literal.LOGIN_NAME,login);
                    userService.setUserNewLogin(oldLogin,login);
                    page = PagePath.MAIN_CLIENT_PAGE;
                } else {
                    request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE, localizedTextExtractor.getText(currentLocale,
                            "USER_WITH_LOGIN_EXISTS_ERROR"));
                    page = PagePath.AUTHORIZATION_PAGE;
                }
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE, localizedTextExtractor.getText(currentLocale,
                    "INVALID_FORM_DATA_ERROR"));
            page = PagePath.AUTHORIZATION_PAGE;
        }
        return page;
    }
}
