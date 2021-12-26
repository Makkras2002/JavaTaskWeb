package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.validator.ProductDataValidator;
import jakarta.servlet.http.HttpServletRequest;

public class CustomProductDataValidator implements ProductDataValidator {
    private static CustomProductDataValidator instance;
    private static final String NUMERIC_STRING_CHECK_REGEX = "\\d+";
    private CustomProductDataValidator(){
    }
    public static CustomProductDataValidator getInstance(){
        if(instance == null){
            instance = new CustomProductDataValidator();
        }
        return instance;
    }
    public boolean validateProductSearchData(HttpServletRequest request){
        String name = request.getParameter(Literal.PRODUCT_NAME);
        String category = request.getParameter(Literal.PRODUCT_CATEGORY);
        String min_price = request.getParameter(Literal.PRODUCT_MIN_PRICE);
        String max_price = request.getParameter(Literal.PRODUCT_MAX_PRICE);
        boolean result = true;
        if(name.length() > 200) {
            result = false;
        }
        if(category.length() > 200) {
            result = false;
        }
        if(!min_price.matches(NUMERIC_STRING_CHECK_REGEX)){
            if(!min_price.equals("")){
                result = false;
            }
        }
        if(!max_price.matches(NUMERIC_STRING_CHECK_REGEX)){
            if(!max_price.equals("")){
                result = false;
            }
        }
        return result;
    }
}
