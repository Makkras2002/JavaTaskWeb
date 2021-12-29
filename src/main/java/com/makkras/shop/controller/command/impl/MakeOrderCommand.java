package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PagePath;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.OrderService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MakeOrderCommand implements CustomCommand {
    Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        OrderService orderService = OrderService.getInstance();
        HttpSession session = request.getSession();
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String login = session.getAttribute(Literal.LOGIN_NAME).toString();
        List<ComponentOrder> componentOrders = (List<ComponentOrder>) session.getAttribute(Literal.ORDER);
        String currentLocale = session.getAttribute(Literal.LOCALE_NAME).toString();
        if(componentOrders.size() > 0){
            try {
                orderService.makeOrderAndSendNotificationMessageOnUserEmail(componentOrders,login,currentLocale);
                page = PagePath.MAIN_CLIENT_PAGE;
                session.setAttribute(Literal.ORDER,new ArrayList<ComponentOrder>());
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            page = PagePath.AUTHORIZATION_PAGE;
            request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                    localizedTextExtractor.getText(currentLocale,"main_client.empty_order"));
        }
        return page;
    }
}
