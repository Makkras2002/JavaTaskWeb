package com.makkras.shop.controller.validator;

import java.util.Map;

public interface ProductDataValidator {
    /**
     * Validate product search data and return boolean result if data is valid.
     *
     * @param rawName     the raw name
     * @param rawCategory the raw category
     * @param rawMinPrice the raw min price
     * @param rawMaxPrice the raw max price
     * @return the boolean validation result
     */
    boolean validateProductSearchData(String rawName,String rawCategory,String rawMinPrice,String rawMaxPrice);

    /**
     * Validate product data for adding and return boolean result if data is valid.
     *
     * @param formValues the form values
     * @param locale     the locale, chosen by current user
     * @return the boolean validation result
     */
    boolean validateProductAddData(Map<String,String> formValues, String locale);
}
