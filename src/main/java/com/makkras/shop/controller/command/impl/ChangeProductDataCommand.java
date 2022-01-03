package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.validator.ProductDataValidator;
import com.makkras.shop.controller.validator.impl.CustomProductDataValidator;
import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.ProductService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeProductDataCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String IMAGE_LOCATION_FOLDER = "\\pictures\\";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String rawId = request.getParameter(Literal.PRODUCT_ID);
        String name = request.getParameter(Literal.PRODUCT_NAME);
        String rawPrice = request.getParameter(Literal.PRODUCT_PRICE);
        String category = request.getParameter(Literal.PRODUCT_CATEGORY);
        String imagePath = request.getParameter(Literal.PRODUCT_IMAGE_PATH);
        String comment = request.getParameter(Literal.PRODUCT_COMMENT);
        Map<String,String> formData = new HashMap<>();
        formData.put(Literal.PRODUCT_ID,rawId);
        formData.put(Literal.PRODUCT_NAME,name);
        formData.put(Literal.PRODUCT_PRICE,rawPrice);
        formData.put(Literal.PRODUCT_CATEGORY,category);
        formData.put(Literal.PRODUCT_IMAGE_PATH,imagePath);
        formData.put(Literal.PRODUCT_COMMENT,comment);
        Boolean isInStock = true;
        if(request.getParameter(Literal.PRODUCT_IN_STOCK_STATUS) ==null){
            isInStock = false;
        }
        ProductService productService = ProductService.getInstance();
        Gson gson = new Gson();
        ProductDataValidator productDataValidator = CustomProductDataValidator.getInstance();
        HttpSession session = request.getSession();
        String currentLocale = session.getAttribute(Literal.LOCALE_NAME).toString();
        try {
            List<ProductCategory> productCategories = productService.findAllProductCategoriesFromDb();
            String productCategoriesInGson = gson.toJson(productCategories);
            request.setAttribute(Literal.PRODUCT_CATEGORIES_FOR_SEARCH,productCategoriesInGson);
            if(productDataValidator.validateProductAddData(formData,currentLocale)) {
                String finalImagePath = null;
                if(!imagePath.isBlank()) {
                    finalImagePath = IMAGE_LOCATION_FOLDER + imagePath;
                }
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(rawPrice));
                Long productId = Long.parseLong(rawId);
                Product product = new Product(productId,name,price,isInStock,finalImagePath,comment,new ProductCategory(category));
                if (productService.updateChangedFieldsInProduct(product)) {
                    page = PagePath.MAIN_ADMIN_PAGE;
                } else {
                    request.setAttribute(Literal.CHANGE_PRODUCT_ERROR_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,"UNEXPECTED_ERROR"));
                    request.setAttribute(Literal.PRODUCT_FOR_CHANGE_DATA,formData);
                    page = PagePath.RAW_CHANGE_PRODUCT;
                }
            } else {
                request.setAttribute(Literal.CHANGE_PRODUCT_ERROR_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
                request.setAttribute(Literal.PRODUCT_FOR_CHANGE_DATA,formData);
                page = PagePath.RAW_CHANGE_PRODUCT;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
        return page;
    }
}
