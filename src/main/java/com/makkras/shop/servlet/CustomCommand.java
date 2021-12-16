package com.makkras.shop.servlet;

import jakarta.servlet.http.HttpServletRequest;

public interface CustomCommand {
    String execute(HttpServletRequest request);
}
