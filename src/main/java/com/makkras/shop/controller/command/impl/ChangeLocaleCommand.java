package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements CustomCommand {
    private static final String MAIN_CLIENT_PAGE_PATH_PART = "mainclient.jsp";
    private static final String BASKET_PAGE_PATH_PART = "basket_show.jsp";
    private static final String MAIN_ADMIN_PAGE_PATH_PART = "mainadmin.jsp";
    private static final String ADD_PRODUCT_PAGE_PATH_PART = "add_product.jsp";
    private static final String USERS_VIEW_PAGE_PATH_PART = "users_admin_view.jsp";
    private static final String ORDERS_VIEW_PAGE_PATH_PART = "orders_admin_view.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter(Literal.PAGE_PATH);
        page = page.replace("http://localhost:8888","");
        if(page.contains(MAIN_CLIENT_PAGE_PATH_PART)) {
            page = PagePath.MAIN_CLIENT_PAGE;
        }
        if(page.contains(BASKET_PAGE_PATH_PART)) {
            page = PagePath.ORDER_PAGE;
        }
        if(page.contains(MAIN_ADMIN_PAGE_PATH_PART)) {
            page = PagePath.MAIN_ADMIN_PAGE;
        }
        if(page.contains(ADD_PRODUCT_PAGE_PATH_PART)) {
            page = PagePath.ADD_PRODUCT_PAGE;
        }
        if(page.contains(USERS_VIEW_PAGE_PATH_PART)) {
            page = PagePath.MAIN_ADMIN_USERS_VIEW_PAGE;
        }
        if(page.contains(ORDERS_VIEW_PAGE_PATH_PART)) {
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
