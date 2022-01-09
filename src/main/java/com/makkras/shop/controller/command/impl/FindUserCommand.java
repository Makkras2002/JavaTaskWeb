package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.validator.impl.CustomUserDataValidator;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.UserService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class FindUserCommand implements CustomCommand {
    private static final String EMPTY_ROLE = "-";
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        String rawLogin = request.getParameter(Literal.LOGIN_NAME);
        String rawEmail = request.getParameter(Literal.EMAIL);
        String role = request.getParameter(Literal.ROLE);
        String rawStatus = request.getParameter(Literal.USER_ONLINE_STATUS);
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        if(CustomUserDataValidator.getInstance().validateUserSearchData(rawLogin,rawEmail)){
            String login = rawLogin;
            String email = rawEmail;
            Boolean onlineStatus = true;
            if(rawStatus.equals(Literal.USER_ONLINE_OFFLINE_STATUS)) {
                onlineStatus = false;
            } else if(rawStatus.equals(EMPTY_ROLE)) {
                onlineStatus = null;
            }
            UserService service = UserService.getInstance();
            try {
                List<User> users  = service.findActiveUsersFromDbByParams(login,email,role,onlineStatus);

                String usersInGson = gson.toJson(users);
                request.setAttribute(Literal.USERS_FOR_ADMIN,usersInGson);
                page = PagePath.RAW_MAIN_ADMIN_USERS_VIEW_PAGE;
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
