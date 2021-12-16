package com.makkras.shop.servlet.impl;

import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.UserService;
import com.makkras.shop.servlet.CustomCommand;
import com.makkras.shop.servlet.util.MessageManager;
import com.makkras.shop.servlet.util.PathManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfRegCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = String.valueOf(request.getSession().getAttribute("login"));
        String password = String.valueOf(request.getSession().getAttribute("password"));
        String email = String.valueOf(request.getSession().getAttribute("email"));
        if(login != null && password != null && email != null){
            try {
                if(UserService.getInstance().checkIfUserIsValidForRegistration(login,email)){
                    UserService.getInstance().registerUser(login,password,email);
                    page = PathManager.getInstance().getProperty("path.page.mainCl");
                } else {
                    page = PathManager.getInstance().getProperty("path.page.auth");
                    request.setAttribute("errorAuthMessage", MessageManager.getInstance().getProperty("message.confRegErrorV2"));
                }
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            try {
                page = PathManager.getInstance().getProperty("path.page.auth");
                request.setAttribute("errorAuthMessage", MessageManager.getInstance().getProperty("message.confRegError"));
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        }
        return page;
    }
}
