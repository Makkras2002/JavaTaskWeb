package com.makkras.shop.servlet;

import com.makkras.shop.servlet.impl.LoginCommand;
import com.makkras.shop.servlet.impl.LogoutCommand;

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
}
