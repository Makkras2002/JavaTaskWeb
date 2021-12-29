package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PagePath;
import com.makkras.shop.entity.ComponentOrder;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class OpenUserOrderCommand implements CustomCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        List<ComponentOrder> componentOrders  = (List<ComponentOrder>) request.getSession().getAttribute(Literal.ORDER);
        String orderInGson = gson.toJson(componentOrders);
        request.setAttribute(Literal.PRODUCTS_IN_ORDER,orderInGson);
        page = PagePath.RAW_ORDER_PAGE;
        return page;
    }
}
