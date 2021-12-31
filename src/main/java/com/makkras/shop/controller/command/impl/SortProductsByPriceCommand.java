package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SortProductsByPriceCommand implements CustomCommand {

    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        ProductService service = ProductService.getInstance();
        try {
            List<Product> productsInStock  = service.findAllProductsInStockFromDbAndSortByPrice();
            String productsInGson = gson.toJson(productsInStock);
            request.setAttribute(Literal.PRODUCTS_IN_STOCK,productsInGson);

            List<ProductCategory> productCategories = service.findAllProductCategoriesFromDb();
            String productCategoriesInGson = gson.toJson(productCategories);
            request.setAttribute(Literal.PRODUCT_CATEGORIES_FOR_SEARCH,productCategoriesInGson);
            page = PagePath.RAW_MAIN_CLIENT_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
