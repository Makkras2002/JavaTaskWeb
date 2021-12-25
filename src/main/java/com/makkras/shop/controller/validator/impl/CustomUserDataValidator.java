package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.validator.UserDataValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomUserDataValidator implements UserDataValidator {
    private static CustomUserDataValidator instance;
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private CustomUserDataValidator(){
    }
    public static CustomUserDataValidator getInstance(){
        if(instance == null){
            instance = new CustomUserDataValidator();
        }
        return instance;
    }
    public boolean validateUserLoginData(HttpServletRequest request){
        boolean isValid = true;
        String login = request.getParameter(Literal.LOGIN_NAME);
        String password = request.getParameter(Literal.PASSWORD);
        if(login.length() > 50 || login.length() < 1){
            isValid = false;
        } else {
            request.setAttribute(Literal.LOGIN_TO_ADD_IN_FORM,login);
        }
        if(password.length() > 20){
            isValid = false;
        } else {
            request.setAttribute(Literal.PASSWORD_TO_ADD_IN_FORM,password);
        }
        return isValid;
    }
    public boolean validateUserChangeLoginData(HttpServletRequest request){
        boolean isValid = true;
        String login = request.getParameter(Literal.LOGIN_NAME);
        if(login.length() > 50 || login.length() < 1){
            isValid = false;
        }
        return isValid;
    }
    public boolean validateUserChangePasswordData(HttpServletRequest request){
        boolean isValid = true;
        String password = request.getParameter(Literal.PASSWORD);
        if(password.length() > 20 || password.length() < 8){
            isValid = false;
        }
        return isValid;
    }
    public boolean validateUserRegistrationData(HttpServletRequest request){
        boolean isValid = true;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        String login = request.getParameter(Literal.LOGIN_NAME);
        String email = request.getParameter(Literal.EMAIL);
        String password = request.getParameter(Literal.PASSWORD);
        Matcher matcher = pattern.matcher(email);
        if(login.length() > 50 || login.length() < 1){
            isValid = false;
        } else {
            request.setAttribute(Literal.LOGIN_TO_ADD_IN_FORM,login);
        }
        if(password.length() > 20 || password.length() < 8){
            isValid = false;
        } else {
            request.setAttribute(Literal.PASSWORD_TO_ADD_IN_FORM,password);
        }
        if(!matcher.matches()){
            isValid = false;
        } else {
            request.setAttribute(Literal.EMAIL_TO_ADD_IN_FORM,email);
        }
        return isValid;
    }

}
