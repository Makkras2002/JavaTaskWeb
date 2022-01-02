package com.makkras.shop.controller.validator;

import java.util.Map;

public interface ProductDataValidator {
    boolean validateProductSearchData(String rawName,String rawCategory,String rawMinPrice,String rawMaxPrice);
    boolean validateProductAddData(Map<String,String> formValues, String locale);
}
