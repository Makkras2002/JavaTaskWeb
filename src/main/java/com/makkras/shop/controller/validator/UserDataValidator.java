package com.makkras.shop.controller.validator;
import java.util.Map;

public interface UserDataValidator {
    /**
     * Validate user login data and return boolean result if data is valid.
     *
     * @param formValues the form values
     * @param locale     the locale, chosen by current user
     * @return the boolean validation result
     */
    boolean validateUserLoginData(Map<String,String> formValues, String locale);

    /**
     * Validate user registration data and return boolean result if data is valid.
     *
     * @param formValues the form values
     * @param locale     the locale, chosen by current user
     * @return the boolean validation result
     */
    boolean validateUserRegistrationData(Map<String,String> formValues, String locale);

    /**
     * Validate user change login data and return boolean result if data is valid.
     *
     * @param newLogin the new login
     * @return the boolean validation result
     */
    boolean validateUserChangeLoginData(String newLogin);

    /**
     * Validate user change password data and return boolean result if data is valid.
     *
     * @param newPassword the new password
     * @return the boolean validation result
     */
    boolean validateUserChangePasswordData(String newPassword);

    /**
     * Validate user search data and return boolean result if data is valid.
     *
     * @param rawLogin the raw login
     * @param rawEmail the raw email
     * @return the boolean validation result
     */
    boolean validateUserSearchData(String rawLogin,String rawEmail);
}
