package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.OrderService;
import com.makkras.shop.service.impl.ProductService;
import com.makkras.shop.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FillMainAdminMenuCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        ProductService service = ProductService.getInstance();
        OrderService orderService = OrderService.getInstance();
        UserService userService = UserService.getInstance();
        try {
            List<Product> products  = service.findAllProductsFromDb();
            String productsInGson = gson.toJson(products);
            request.setAttribute(Literal.PRODUCTS_FOR_ADMIN,productsInGson);

            List<User> users  = userService.findAllUsers();
            String usersInGson = gson.toJson(users);
            request.setAttribute(Literal.USERS_FOR_ADMIN,usersInGson);

            List<CompleteOrder> orders  = orderService.findAllOrdersFromDb();
            String ordersInGson = gson.toJson(orders);
            request.setAttribute(Literal.ORDERS_FOR_ADMIN,ordersInGson);


            page = PagePath.RAW_MAIN_ADMIN_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
