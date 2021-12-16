package com.makkras.shop.servlet;

import com.makkras.shop.servlet.impl.ConfRegCommand;
import com.makkras.shop.servlet.impl.LoginCommand;
import com.makkras.shop.servlet.impl.LogoutCommand;
import com.makkras.shop.servlet.impl.RegisterCommand;

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
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    CONFREG {
        {
            this.command = new ConfRegCommand();
        }
    };
    CustomCommand command;
    public CustomCommand getCurrentCommand(){
        return command;
    }
    public static CustomCommand defineCommand(String requestCommand){
        CustomCommand command;
        CommandEnum currentEnum = CommandEnum.valueOf(requestCommand.toUpperCase());
        command =currentEnum.getCurrentCommand();
        return command;
    }
}
