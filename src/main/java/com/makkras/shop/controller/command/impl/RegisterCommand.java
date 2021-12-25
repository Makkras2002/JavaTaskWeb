package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.validator.impl.CustomUserDataValidator;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        String login = request.getParameter(Literal.LOGIN_NAME);
        String email = request.getParameter(Literal.EMAIL);
        String password = request.getParameter(Literal.PASSWORD);
        UserService userService = UserService.getInstance();
        String page = null;
        try {
            if(CustomUserDataValidator.getInstance().validateUserRegistrationData(request)){
                if(request.getSession().getAttribute(Literal.LOGIN_NAME) == null){
                    if(userService.checkIfUserIsValidForRegistration(login,email)){
                        request.getSession().setAttribute(Literal.LOGIN_NAME, login);
                        request.getSession().setAttribute(Literal.PASSWORD, password);
                        request.getSession().setAttribute(Literal.EMAIL, email);
                        request.getSession().setAttribute(Literal.ORDER, null);
                        request.getSession().setAttribute(Literal.ROLE, UserRole.CLIENT);
                        userService.sendMessageAboutSuccessFullRegistrationOnUserEmail(login,email,currentLocale);
                        request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                                localizedTextExtractor.getText(currentLocale,"REGISTRATION_CONFIRMATION_AWAIT"));
                        page = Literal.AUTHORIZATION_PAGE;
                    } else {
                        request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                                localizedTextExtractor.getText(currentLocale,"REGISTRATION_ERROR"));
                        page = Literal.REGISTRATION_PAGE;
                    }
                } else {
                    request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,"FIRST_LOGOUT_ERROR"));
                    page = Literal.REGISTRATION_PAGE;
                }
            } else {
                request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
                page = Literal.REGISTRATION_PAGE;
            }
        } catch (ServiceException e){
            logger.error(e.getMessage());
        }
        return page;
    }
}
