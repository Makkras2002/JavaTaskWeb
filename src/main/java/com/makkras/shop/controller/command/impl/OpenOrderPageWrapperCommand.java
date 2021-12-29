package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class OpenOrderPageWrapperCommand implements CustomCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.ORDER_PAGE;
        return page;
    }
}
