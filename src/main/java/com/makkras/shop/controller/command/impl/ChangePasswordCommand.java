package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PagePath;
import com.makkras.shop.controller.validator.impl.CustomUserDataValidator;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangePasswordCommand implements CustomCommand {
    Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        HttpSession session = request.getSession();
        String password = request.getParameter(Literal.PASSWORD);
        String login = session.getAttribute(Literal.LOGIN_NAME).toString();
        String currentLocale = session.getAttribute(Literal.LOCALE_NAME).toString();
        String page = null;
        if(CustomUserDataValidator.getInstance().validateUserChangePasswordData(password)){
            try {
                UserService.getInstance().setUserNewPassword(login,password);
                session.setAttribute(Literal.PASSWORD,password);
                page = PagePath.MAIN_CLIENT_PAGE;
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                    localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
            page = PagePath.AUTHORIZATION_PAGE;
        }
        return page;
    }
}
