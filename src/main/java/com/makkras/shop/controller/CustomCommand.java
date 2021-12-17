package com.makkras.shop.controller;

import jakarta.servlet.http.HttpServletRequest;

public interface CustomCommand {
    String execute(HttpServletRequest request);
}
