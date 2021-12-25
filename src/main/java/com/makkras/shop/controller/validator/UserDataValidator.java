package com.makkras.shop.controller.validator;

import jakarta.servlet.http.HttpServletRequest;

public interface UserDataValidator {
    boolean validateUserLoginData(HttpServletRequest request);
    boolean validateUserRegistrationData(HttpServletRequest request);
    boolean validateUserChangeLoginData(HttpServletRequest request);
    boolean validateUserChangePasswordData(HttpServletRequest request);
}
