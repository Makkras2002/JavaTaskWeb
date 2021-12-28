package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PagePath;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.controller.command.CustomCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LogoutCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Optional<String> currentLocale = Optional.empty();
        try {
            UserService.getInstance().setUserStatusNotOnlineInDb(String.valueOf(request.getSession().getAttribute(Literal.LOGIN_NAME)));
            page = PagePath.AUTHORIZATION_PAGE;
            currentLocale = Optional.ofNullable(String.valueOf(request.getSession().getAttribute(Literal.LOCALE_NAME)));
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        request.getSession().invalidate();
        if(currentLocale.isPresent()){
            if(currentLocale.get().equals(Literal.BRITISH_LOCALE)){
                request.getSession().setAttribute(Literal.LOCALE_NAME,Literal.BRITISH_LOCALE);
            }
        }
        return page;
    }
}
