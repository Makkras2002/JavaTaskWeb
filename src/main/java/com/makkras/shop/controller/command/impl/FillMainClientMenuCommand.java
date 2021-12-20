package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.entity.Product;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FillMainClientMenuCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        ProductService service = ProductService.getInstance();
        try {
            List<Product> productsInStock  = service.getAllProductsInStockFromDb();
            String productsInGson = gson.toJson(productsInStock);
            request.setAttribute(Literal.PRODUCTS_IN_STOCK,productsInGson);
            page = Literal.RAW_MAIN_CLIENT_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
