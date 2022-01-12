package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.validator.UserDataValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CustomUserDataValidatorTest {
    private UserDataValidator userDataValidator;
    private Map<String,String> loginFormValues;
    private Map<String,String> registrationFormValues;
    private String login;
    private String password;
    private String email;
    @BeforeClass
    public void setUp() {
        userDataValidator = CustomUserDataValidator.getInstance();
        login = "Maksim";
        password = "1231231223";
        email = "max2002shpak@gmail.com";
        loginFormValues = new HashMap<>();
        loginFormValues.put(Literal.LOGIN_TO_ADD_IN_FORM,login);
        loginFormValues.put(Literal.PASSWORD_TO_ADD_IN_FORM,password);
        registrationFormValues = new HashMap<>();
        registrationFormValues.put(Literal.LOGIN_TO_ADD_IN_FORM,login);
        registrationFormValues.put(Literal.PASSWORD_TO_ADD_IN_FORM,password);
        registrationFormValues.put(Literal.EMAIL_TO_ADD_IN_FORM,email);
    }

    @Test
    public void validateUserLoginData() {
        assertTrue(userDataValidator.validateUserLoginData(loginFormValues,Literal.BRITISH_LOCALE));
    }


    @Test
    public void validateUserRegistrationData() {
        assertTrue(userDataValidator.validateUserRegistrationData(registrationFormValues,Literal.BRITISH_LOCALE));
    }

    @Test
    public void validateUserChangeLoginData() {
        assertTrue(userDataValidator.validateUserChangeLoginData(login));
    }

    @Test
    public void validateUserChangePasswordData() {
        assertTrue(userDataValidator.validateUserChangePasswordData(password));
    }

    @Test
    public void validateUserSearchData() {
        assertTrue(userDataValidator.validateUserSearchData(login,email));
    }
}