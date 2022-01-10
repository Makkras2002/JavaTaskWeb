package com.makkras.shop.controller.validator;

public interface OrderDataValidator {
    boolean validateOrderSearchData (String rawLogin,String rawStartDate,String rawEndDate);
}
