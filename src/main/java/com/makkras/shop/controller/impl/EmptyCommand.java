package com.makkras.shop.controller.impl;

import com.makkras.shop.controller.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.MessageManager;
import com.makkras.shop.controller.util.PathManager;
import com.makkras.shop.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmptyCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try {
            page = PathManager.getInstance().getProperty("path.page.auth");
            request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE, MessageManager.getInstance().getProperty("message.commandError"));
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
