package com.makkras.shop.servlet;

import com.makkras.shop.servlet.impl.LoginCommand;
import com.makkras.shop.servlet.impl.LogoutCommand;
import jakarta.servlet.http.HttpServletRequest;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand(){
        return command;
    }
    public static ActionCommand defineCommand(String requestCommand){
        ActionCommand command;
        CommandEnum currentEnum = CommandEnum.valueOf(requestCommand.toUpperCase());
        command =currentEnum.getCurrentCommand();
        return command;
    }
}
