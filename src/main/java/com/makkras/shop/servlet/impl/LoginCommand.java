package com.makkras.shop.servlet.impl;

import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.UserService;
import com.makkras.shop.servlet.ActionCommand;
import com.makkras.shop.servlet.util.MessageManager;
import com.makkras.shop.servlet.util.PathManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String LOGIN_NAME = "login";
    private static final String LOGIN_PASSWORD = "password";
    private static final String ENTER_AS_ADMIN = "enterAsAdmin";
    private static final String DEFAULT_LOCALE = "RU";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(LOGIN_NAME);
        String password = request.getParameter(LOGIN_PASSWORD);
        Boolean enterAsAdmin = true;
        if(request.getParameter(ENTER_AS_ADMIN) ==null){
            enterAsAdmin =false;
        }
        try {
            Optional<User> foundUser = UserService.getInstance().findUserWithLoginAndPassword(login,password,enterAsAdmin);
            if(foundUser.isPresent()){
                request.getSession().setAttribute("login", foundUser.get().getLogin());
                request.getSession().setAttribute("password", foundUser.get().getPassword());
                request.getSession().setAttribute("email", foundUser.get().getEmail());
                request.getSession().setAttribute("locale", DEFAULT_LOCALE);
                request.getSession().setAttribute("order", null);
                page = PathManager.getInstance().getProperty("path.page.mainCl");
            }else {
                request.setAttribute("errorAuthMessage", MessageManager.getInstance().getProperty("message.loginError"));
                page = PathManager.getInstance().getProperty("path.page.auth");
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
