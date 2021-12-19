package com.makkras.shop.controller;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.command.impl.*;

public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    CONFIRM_REGISTRATION {
        {
            this.command = new ConfRegCommand();
        }
    },
    EMPTY {
        {
            this.command = new EmptyCommand();
        }
    };
    CustomCommand command;
    public CustomCommand getCurrentCommand(){
        return command;
    }
    public static CustomCommand defineCommand(String requestCommand){
        CustomCommand command;
        CommandType currentEnum;
        try {
            currentEnum = CommandType.valueOf(requestCommand.toUpperCase());
        } catch (IllegalArgumentException e){
            currentEnum = CommandType.EMPTY;
        }
        command =currentEnum.getCurrentCommand();
        return command;
    }
}
