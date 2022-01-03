package com.makkras.shop.controller.validator.impl;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.validator.ProductDataValidator;
import com.makkras.shop.util.locale.LocalizedTextExtractor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomProductDataValidator implements ProductDataValidator {
    private static CustomProductDataValidator instance;
    private static final String NUMERIC_STRING_CHECK_REGEX = "([-+]?[0-9]*\\.?[0-9]+)";
    private static final String IMAGE_FILE_PATH_REGEX = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";
    private static final int MAX_STRING_LENGTH = 200;
    private static final int MIN_STRING_LENGTH = 1;
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
    public boolean validateProductAddData(Map<String,String> formValues, String locale) {
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        boolean result = true;
        String name = formValues.get(Literal.PRODUCT_NAME);
        String rawCategory = formValues.get(Literal.PRODUCT_CATEGORY);
        String price = formValues.get(Literal.PRODUCT_PRICE);
        String imagePath = formValues.get(Literal.PRODUCT_IMAGE_PATH);
        String comment = formValues.get(Literal.PRODUCT_COMMENT);
        if(name.length() > MAX_STRING_LENGTH || name.length() < MIN_STRING_LENGTH) {
            formValues.replace(Literal.PRODUCT_NAME,name,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            result = false;
        }
        if(rawCategory.length() > MAX_STRING_LENGTH || rawCategory.length() < MIN_STRING_LENGTH) {
            formValues.replace(Literal.PRODUCT_CATEGORY,rawCategory,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            result = false;
        }
        if(!price.matches(NUMERIC_STRING_CHECK_REGEX)){
            System.out.println(price);
            formValues.replace(Literal.PRODUCT_PRICE,price,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            result = false;
        }
        Pattern p = Pattern.compile(IMAGE_FILE_PATH_REGEX);
        Matcher matcher = p.matcher(imagePath);
        if(!matcher.matches()) {
            if(!imagePath.isBlank()) {
                result = false;
            }
        }
        if(comment.length() < MIN_STRING_LENGTH) {
            formValues.replace(Literal.PRODUCT_COMMENT,comment,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            result = false;
        }
        return result;
    }
}
