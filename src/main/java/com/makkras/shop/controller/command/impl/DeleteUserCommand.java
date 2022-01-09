package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DeleteUserCommand implements CustomCommand {

    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        String login = request.getParameter(Literal.LOGIN_NAME);
        UserService userService = UserService.getInstance();
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        try {
            List<User> users  = userService.findAllUsers();
            String usersInGson = gson.toJson(users);
            request.setAttribute(Literal.USERS_FOR_ADMIN,usersInGson);
            if(!userService.deleteUser(login)) {
                request.setAttribute(Literal.ERROR_USER_DELETE_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"USER_DELETE_ERROR"));
            }
            page = PagePath.RAW_MAIN_ADMIN_USERS_VIEW_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
