package com.makkras.shop.controller.validator;

public interface ProductDataValidator {
    boolean validateProductSearchData(String rawName,String rawCategory,String rawMinPrice,String rawMaxPrice);
}
