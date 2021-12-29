package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PagePath;
import com.makkras.shop.controller.validator.impl.CustomUserDataValidator;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LoginCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        String page = null;
        String login = request.getParameter(Literal.LOGIN_NAME);
        String password = request.getParameter(Literal.PASSWORD);
        Map<String,String> formValues = new HashMap<>();
        formValues.put(Literal.LOGIN_TO_ADD_IN_FORM,login);
        formValues.put(Literal.PASSWORD_TO_ADD_IN_FORM,password);
        Boolean enterAsAdmin = true;
        if(request.getParameter(Literal.ENTER_AS_ADMIN) ==null){
            enterAsAdmin = false;
        }
        try {
            if(CustomUserDataValidator.getInstance().validateUserLoginData(formValues, currentLocale)){
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
                        List<ComponentOrder> componentOrders = new ArrayList<>();
                        request.getSession().setAttribute(Literal.ORDER, componentOrders);
                        page = PagePath.MAIN_CLIENT_PAGE;
                    }else {
                        request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                                localizedTextExtractor.getText(currentLocale,"LOGIN_ERROR"));
                        page = PagePath.AUTHORIZATION_PAGE;
                    }
                } else {
                    request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,"FIRST_LOGOUT_ERROR"));
                    page = PagePath.AUTHORIZATION_PAGE;
                }
            } else {
                request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
                request.setAttribute(Literal.LOGIN_FORM_DATA_MAP,formValues);
                page = PagePath.AUTHORIZATION_PAGE;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
