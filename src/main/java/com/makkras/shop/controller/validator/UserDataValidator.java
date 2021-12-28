package com.makkras.shop.controller.validator;
import java.util.Map;

public interface UserDataValidator {
    boolean validateUserLoginData(Map<String,String> formValues, String locale);
    boolean validateUserRegistrationData(Map<String,String> formValues, String locale);
    boolean validateUserChangeLoginData(String newLogin);
    boolean validateUserChangePasswordData(String newPassword);
}
