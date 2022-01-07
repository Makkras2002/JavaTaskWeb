package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import jakarta.servlet.http.HttpServletRequest;

public class FillAdminAddingMenuCommand implements CustomCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.ADMIN_REGISTRATION_PAGE;
    }
}
