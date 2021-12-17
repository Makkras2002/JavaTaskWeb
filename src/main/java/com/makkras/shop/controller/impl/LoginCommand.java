package com.makkras.shop.controller.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.UserService;
import com.makkras.shop.controller.CustomCommand;
import com.makkras.shop.controller.util.MessageManager;
import com.makkras.shop.controller.util.PathManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(Literal.LOGIN_NAME);
        String password = request.getParameter(Literal.PASSWORD);
        Boolean enterAsAdmin = true;
        if(request.getParameter(Literal.ENTER_AS_ADMIN) ==null){
            enterAsAdmin =false;
        }
        try {
            Optional<User> foundUser = UserService.getInstance().findUserWithLoginAndPassword(login,password,enterAsAdmin);
            if(foundUser.isPresent()){
                request.getSession().setAttribute(Literal.LOGIN_NAME, foundUser.get().getLogin());
                request.getSession().setAttribute(Literal.PASSWORD, foundUser.get().getPassword());
                request.getSession().setAttribute(Literal.EMAIL, foundUser.get().getEmail());
                request.getSession().setAttribute(Literal.LOCALE_NAME, Literal.DEFAULT_LOCALE);
                request.getSession().setAttribute(Literal.ORDER, null);
                page = PathManager.getInstance().getProperty("path.page.mainCl");
            }else {
                request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE, MessageManager.getInstance().getProperty("message.loginError"));
                page = PathManager.getInstance().getProperty("path.page.auth");
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
