package com.makkras.shop.controller.validator;

/**
 * The interface Order data validator.
 */
public interface OrderDataValidator {
    /**
     * Validate order search data and return boolean result if data is valid.
     *
     * @param rawLogin     the raw login
     * @param rawStartDate the raw start date
     * @param rawEndDate   the raw end date
     * @return the boolean validation result
     */
    boolean validateOrderSearchData (String rawLogin,String rawStartDate,String rawEndDate);
}
