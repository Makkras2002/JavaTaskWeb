package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements CustomCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter(Literal.PAGE_PATH);
        page = page.replace("http://localhost:8888","");
        if(page.contains("mainclient.jsp")) {
            page = PagePath.MAIN_CLIENT_PAGE;
        }
        if(page.contains("basket_show.jsp")) {
            page = PagePath.ORDER_PAGE;
        }
        if(page.contains("mainadmin.jsp")) {
            page = PagePath.MAIN_ADMIN_PAGE;
        }
        if(page.contains("add_product.jsp")) {
            page = PagePath.ADD_PRODUCT_PAGE;
        }
        if(page.contains("users_admin_view.jsp")) {
            page = PagePath.MAIN_ADMIN_USERS_VIEW_PAGE;
        }
        if(page.contains("orders_admin_view.jsp")) {
            page = PagePath.MAIN_ADMIN_ORDERS_VIEW_PAGE;
        }
        if(request.getSession().getAttribute(Literal.LOCALE_NAME) != null){
            if(request.getSession().getAttribute(Literal.LOCALE_NAME).equals(Literal.BRITISH_LOCALE)){
                request.getSession().setAttribute(Literal.LOCALE_NAME,Literal.DEFAULT_LOCALE);
            } else {
                request.getSession().setAttribute(Literal.LOCALE_NAME,Literal.BRITISH_LOCALE);
            }
        }
        return page;
    }
}
