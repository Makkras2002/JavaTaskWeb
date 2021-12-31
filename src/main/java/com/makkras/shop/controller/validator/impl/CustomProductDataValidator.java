package com.makkras.shop.controller.validator.impl;
import com.makkras.shop.controller.validator.ProductDataValidator;


public class CustomProductDataValidator implements ProductDataValidator {
    private static CustomProductDataValidator instance;
    private static final String NUMERIC_STRING_CHECK_REGEX = "\\d+";
    private static final int MAX_STRING_LENGTH = 200;
    private CustomProductDataValidator(){
    }
    public static CustomProductDataValidator getInstance(){
        if(instance == null){
            instance = new CustomProductDataValidator();
        }
        return instance;
    }
    public boolean validateProductSearchData(String rawName,String rawCategory,String rawMinPrice,String rawMaxPrice){

        boolean result = true;
        if(rawName.length() > MAX_STRING_LENGTH) {
            result = false;
        }
        if(rawCategory.length() > MAX_STRING_LENGTH) {
            result = false;
        }
        if(!rawMinPrice.matches(NUMERIC_STRING_CHECK_REGEX)){
            if(!rawMinPrice.isBlank()){
                result = false;
            }
        }
        if(!rawMaxPrice.matches(NUMERIC_STRING_CHECK_REGEX)){
            if(!rawMaxPrice.isBlank()){
                result = false;
            }
        }
        return result;
    }
}
