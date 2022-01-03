package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FillAdminOrdersViewMenuCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        OrderService orderService = OrderService.getInstance();
        try {
            List<CompleteOrder> orders  = orderService.findAllOrdersFromDb();
            String ordersInGson = gson.toJson(orders);
            request.setAttribute(Literal.ORDERS_FOR_ADMIN,ordersInGson);
            page = PagePath.RAW_MAIN_ADMIN_ORDERS_VIEW_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
