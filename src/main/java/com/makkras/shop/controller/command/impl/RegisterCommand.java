package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.validator.impl.CustomUserDataValidator;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        String login = request.getParameter(Literal.LOGIN_NAME);
        String email = request.getParameter(Literal.EMAIL);
        String password = request.getParameter(Literal.PASSWORD);
        Map<String,String> formValues = new HashMap<>();
        formValues.put(Literal.LOGIN_TO_ADD_IN_FORM,login);
        formValues.put(Literal.PASSWORD_TO_ADD_IN_FORM,password);
        formValues.put(Literal.EMAIL_TO_ADD_IN_FORM,email);
        UserService userService = UserService.getInstance();
        String page = null;
        try {
            if(CustomUserDataValidator.getInstance().validateUserRegistrationData(formValues, currentLocale)){
                if(request.getSession().getAttribute(Literal.LOGIN_NAME) == null){
                    if(userService.checkIfUserIsValidForRegistration(login,email)){
                        request.getSession().setAttribute(Literal.LOGIN_NAME, login);
                        request.getSession().setAttribute(Literal.PASSWORD, password);
                        request.getSession().setAttribute(Literal.EMAIL, email);
                        List<ComponentOrder> componentOrders = new ArrayList<>();
                        request.getSession().setAttribute(Literal.ORDER, componentOrders);
                        request.getSession().setAttribute(Literal.ROLE, UserRole.CLIENT);
                        request.getSession().setAttribute(Literal.PREPARED_FOR_REGISTRATION, true);
                        userService.sendMessageAboutSuccessFullRegistrationOnUserEmail(login,email,currentLocale);
                        request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                                localizedTextExtractor.getText(currentLocale,"REGISTRATION_CONFIRMATION_AWAIT"));
                        page = PagePath.AUTHORIZATION_PAGE;
                    } else {
                        request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                                localizedTextExtractor.getText(currentLocale,"REGISTRATION_ERROR"));
                        page = PagePath.REGISTRATION_PAGE;
                    }
                } else {
                    request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,"FIRST_LOGOUT_ERROR"));
                    page = PagePath.REGISTRATION_PAGE;
                }
            } else {
                request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
                request.setAttribute(Literal.REGISTRATION_FORM_DATA_MAP,formValues);
                page = PagePath.REGISTRATION_PAGE;
            }
        } catch (ServiceException e){
            logger.error(e.getMessage());
        }
        return page;
    }
}
