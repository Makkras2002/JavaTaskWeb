package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.validator.impl.CustomUserDataValidator;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterAdminCommand implements CustomCommand {

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
                System.out.println("juu");
                if(userService.checkIfUserIsValidForRegistration(login,email)){
                    userService.registerAdmin(login,password,email);
                    page = PagePath.MAIN_ADMIN_PAGE;
                } else {
                    request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,"REGISTRATION_ERROR"));
                    page = PagePath.ADMIN_REGISTRATION_PAGE;
                }
            } else {
                request.setAttribute(Literal.ERROR_REGISTRATION_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
                request.setAttribute(Literal.REGISTRATION_FORM_DATA_MAP,formValues);
                page = PagePath.ADMIN_REGISTRATION_PAGE;
            }
        } catch (ServiceException e){
            logger.error(e.getMessage());
        }
        return page;
    }
}
