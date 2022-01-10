package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.validator.OrderDataValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomOrderDataValidator implements OrderDataValidator {
    private static final String DATE_PATTERN = """
                    ^\\d{4}-\\d{2}-\\d{2}$""";
    private static final int MAX_LOGIN_LENGTH = 50;
    private static CustomOrderDataValidator instance;

    private CustomOrderDataValidator() {
    }
    public static CustomOrderDataValidator getInstance() {
        if(instance == null) {
            instance = new CustomOrderDataValidator();
        }
        return instance;
    }

    public boolean validateOrderSearchData(String rawLogin,String rawStartDate,String rawEndDate) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher startMatcher = pattern.matcher(rawStartDate);
        Matcher endMatcher = pattern.matcher(rawEndDate);
        boolean result = true;
        if(rawLogin.length() > MAX_LOGIN_LENGTH) {
            result = false;
        }
        if(!rawStartDate.isBlank()) {
            if(!startMatcher.matches()){
                result = false;
            }
        }
        if(!rawEndDate.isBlank()) {
            if(!endMatcher.matches()){
                result = false;
            }
        }
        return result;
    }
}
