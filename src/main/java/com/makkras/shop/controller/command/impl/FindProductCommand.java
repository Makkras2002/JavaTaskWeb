package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.validator.impl.CustomProductDataValidator;
import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.ProductService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class FindProductCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        if(CustomProductDataValidator.getInstance().validateProductSearchData(request)){
            String name = request.getParameter(Literal.PRODUCT_NAME);
            String category = request.getParameter(Literal.PRODUCT_CATEGORY);
            BigDecimal min_price;
            BigDecimal max_price;
            if(!request.getParameter(Literal.PRODUCT_MIN_PRICE).equals("")){
                min_price = BigDecimal.valueOf(Double.parseDouble(request.getParameter(Literal.PRODUCT_MIN_PRICE)));
            } else {
                min_price = BigDecimal.valueOf(0);
            }
            if(!request.getParameter(Literal.PRODUCT_MAX_PRICE).equals("")){
                max_price = BigDecimal.valueOf(Double.parseDouble(request.getParameter(Literal.PRODUCT_MAX_PRICE)));
            } else {
                max_price = BigDecimal.valueOf(0);
            }
            ProductService service = ProductService.getInstance();
            try {
                List<Product> productsInStock  = service.findProductsInStockFromDbByParams(name,category,min_price,max_price);
                String productsInGson = gson.toJson(productsInStock);
                request.setAttribute(Literal.PRODUCTS_IN_STOCK,productsInGson);

                List<ProductCategory> productCategories = service.getAllProductCategoriesFromDb();
                String productCategoriesInGson = gson.toJson(productCategories);
                request.setAttribute(Literal.PRODUCT_CATEGORIES_FOR_SEARCH,productCategoriesInGson);
                page = Literal.RAW_MAIN_CLIENT_PAGE;
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                    localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
            page = Literal.AUTHORIZATION_PAGE;
        }
        return page;
    }
}
