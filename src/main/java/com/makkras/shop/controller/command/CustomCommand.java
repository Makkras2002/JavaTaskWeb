package com.makkras.shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public interface CustomCommand {
    String execute(HttpServletRequest request);
}
