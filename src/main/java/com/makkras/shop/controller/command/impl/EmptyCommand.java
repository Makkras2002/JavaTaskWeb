package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmptyCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        page = Literal.AUTHORIZATION_PAGE;
        request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE, Literal.COMMAND_ERROR);
        return page;
    }
}
