package com.makkras.shop.servlet;

import jakarta.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand command;
        String action = request.getParameter("command");
        CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
        command =currentEnum.getCurrentCommand();
        return command;
    }
}
