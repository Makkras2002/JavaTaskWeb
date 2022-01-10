package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.OrderService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CompleteOrderCommand implements CustomCommand {

    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        Long orderId = Long.parseLong(request.getParameter(Literal.ORDER_ID));
        OrderService orderService = OrderService.getInstance();
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        try {
            if(!orderService.completeOrder(orderId)) {
                request.setAttribute(Literal.ERROR_ORDER_COMPLETE_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"ORDER_COMPLETE_ERROR"));
            }
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
