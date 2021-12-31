package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.entity.ComponentOrder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class RemoveProductFromOrderCommand implements CustomCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.ORDER_PAGE;
        Long productId = Long.parseLong(request.getParameter(Literal.PRODUCT_ID));
        Long orderedAmount = Long.parseLong(request.getParameter(Literal.PRODUCT_AMOUNT));
        HttpSession session = request.getSession();
        List<ComponentOrder> orderList = (List<ComponentOrder>) session.getAttribute(Literal.ORDER);
        int counter = 0;
        while (counter < orderList.size()){
            if(orderList.get(counter).getProduct().getProductId().equals(productId) &&
                    orderList.get(counter).getOrderedProductAmount().equals(orderedAmount)) {
                orderList.remove(counter);
                break;
            }
            counter++;
        }
        return page;
    }
}
