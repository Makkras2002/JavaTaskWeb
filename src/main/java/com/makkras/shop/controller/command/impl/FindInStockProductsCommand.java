package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.entity.Product;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindInStockProductsCommand implements CustomCommand {

    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        ProductService service = ProductService.getInstance();
        try {
            List<Product> products  = service.findAllProductsInStockFromDb();
            String productsInGson = gson.toJson(products);
            request.setAttribute(Literal.PRODUCTS_FOR_ADMIN,productsInGson);
            page = PagePath.RAW_MAIN_ADMIN_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
