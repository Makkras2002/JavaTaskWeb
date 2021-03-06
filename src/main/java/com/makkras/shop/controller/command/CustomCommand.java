package com.makkras.shop.controller.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The interface Custom command.
 */
public interface CustomCommand {
    /**
     * Execute command and return string page.
     *
     * @param request the request
     * @return the string page
     */
    String execute(HttpServletRequest request);
}
