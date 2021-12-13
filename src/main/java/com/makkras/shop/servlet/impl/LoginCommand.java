package com.makkras.shop.servlet.impl;

import com.makkras.shop.dao.UserDao;
import com.makkras.shop.dao.impl.UserDaoImpl;
import com.makkras.shop.encryptor.impl.PasswordEncryptor;
import com.makkras.shop.entity.User;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.servlet.ActionCommand;
import com.makkras.shop.servlet.util.MessageManager;
import com.makkras.shop.servlet.util.PathManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoginCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String LOGIN_NAME = "login";
    private static final String LOGIN_PASSWORD = "password";
    private static final String ENTER_AS_ADMIN = "enterAsAdmin";
    private static final String DEFAULT_LOCALE = "RU";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        PasswordEncryptor passwordEncryptor =new PasswordEncryptor();
        String login = request.getParameter(LOGIN_NAME);
        String password = passwordEncryptor.encryptPassword(request.getParameter(LOGIN_PASSWORD));
        Boolean enterAsAdmin = true;
        if(request.getParameter(ENTER_AS_ADMIN) ==null){
            enterAsAdmin =false;
        }
        UserDao userDao = new UserDaoImpl();
        try {
            List<User> users = userDao.findUserWithSuchLogin(login);
            logger.info(users);
            if(users.size() == 1){
                if(users.get(0).getPassword().equals(password)){
                    if(!enterAsAdmin){
                        request.getSession().setAttribute("login", users.get(0).getLogin());
                        request.getSession().setAttribute("password", users.get(0).getPassword());
                        request.getSession().setAttribute("email", users.get(0).getEmail());
                        request.getSession().setAttribute("locale", DEFAULT_LOCALE);
                        request.getSession().setAttribute("order", null);
                        userDao.updateOnlineStatus(LOGIN_NAME,true);
                        page = PathManager.getInstance().getProperty("path.page.mainCl");
                    }else {
                        if(users.get(0).getUserRoleName().equals(UserRole.ADMIN.toString())){
                            request.getSession().setAttribute("login", users.get(0).getLogin());
                            request.getSession().setAttribute("password", users.get(0).getPassword());
                            request.getSession().setAttribute("email", users.get(0).getEmail());
                            request.getSession().setAttribute("locale", DEFAULT_LOCALE);
                            request.getSession().setAttribute("order", null);
                            userDao.updateOnlineStatus(LOGIN_NAME,true);
                            page = PathManager.getInstance().getProperty("path.page.mainCl");
                        }else {
                            request.setAttribute("errorAuthMessage",MessageManager.getInstance().getProperty("message.loginError"));
                            page = PathManager.getInstance().getProperty("path.page.auth");
                        }
                    }
                } else {
                    request.setAttribute("errorAuthMessage",MessageManager.getInstance().getProperty("message.loginError"));
                    page = PathManager.getInstance().getProperty("path.page.auth");
                }
            }else {
                request.setAttribute("errorAuthMessage", MessageManager.getInstance().getProperty("message.loginError"));
                page = PathManager.getInstance().getProperty("path.page.auth");
            }
        } catch (InteractionException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
