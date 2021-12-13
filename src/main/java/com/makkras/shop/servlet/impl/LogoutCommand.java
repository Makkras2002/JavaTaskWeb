package com.makkras.shop.servlet.impl;

import com.makkras.shop.servlet.ActionCommand;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page ="path.page.auth";
        request.getSession().invalidate();
        return page;
    }
}
