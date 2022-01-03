package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FillChangeProductDataMenuCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Long productId = Long.parseLong(request.getParameter(Literal.PRODUCT_ID));
        ProductService productService = ProductService.getInstance();
        try {
            Optional<Product> product = productService.findProductById(productId);
            if(product.isPresent()) {
                Map<String,String> formData = new HashMap<>();
                formData.put(Literal.PRODUCT_ID,product.get().getProductId().toString());
                formData.put(Literal.PRODUCT_NAME,product.get().getProductName());
                String stringPrice = String.valueOf(product.get().getProductPrice());
                formData.put(Literal.PRODUCT_PRICE,stringPrice);
                formData.put(Literal.PRODUCT_CATEGORY,product.get().getProductCategory().getCategory());
                formData.put(Literal.PRODUCT_IMAGE_PATH,product.get().getPicturePath());
                formData.put(Literal.PRODUCT_COMMENT,product.get().getProductComment());
                request.setAttribute(Literal.PRODUCT_FOR_CHANGE_DATA,formData);
                Gson gson = new Gson();
                List<ProductCategory> productCategories = productService.findAllProductCategoriesFromDb();
                String productCategoriesInGson = gson.toJson(productCategories);
                request.setAttribute(Literal.PRODUCT_CATEGORIES_FOR_SEARCH,productCategoriesInGson);

                page = PagePath.RAW_CHANGE_PRODUCT;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
        return page;
    }
}
