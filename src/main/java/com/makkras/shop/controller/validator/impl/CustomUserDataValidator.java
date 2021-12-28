package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.validator.UserDataValidator;
import com.makkras.shop.util.locale.LocalizedTextExtractor;

import java.util.Map;
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
    public boolean validateUserLoginData(Map<String,String> formValues, String locale){
        boolean isValid = true;
        String login = formValues.get(Literal.LOGIN_TO_ADD_IN_FORM);
        String password = formValues.get(Literal.PASSWORD_TO_ADD_IN_FORM);
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        if(login.length() > 50 || login.length() < 1){
            formValues.replace(Literal.LOGIN_TO_ADD_IN_FORM,login,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        if(password.length() > 20){
            formValues.replace(Literal.PASSWORD_TO_ADD_IN_FORM,password,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        return isValid;
    }
    public boolean validateUserChangeLoginData(String newLogin){
        boolean isValid = true;
        String login = newLogin;
        if(login.length() > 50 || login.length() < 1){
            isValid = false;
        }
        return isValid;
    }
    public boolean validateUserChangePasswordData(String newPassword){
        boolean isValid = true;
        String password = newPassword;
        if(password.length() > 20 || password.length() < 8){
            isValid = false;
        }
        return isValid;
    }
    public boolean validateUserRegistrationData(Map<String,String> formValues, String locale){
        boolean isValid = true;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        String login =formValues.get(Literal.LOGIN_TO_ADD_IN_FORM);
        String email = formValues.get(Literal.EMAIL_TO_ADD_IN_FORM);
        String password = formValues.get(Literal.PASSWORD_TO_ADD_IN_FORM);
        Matcher matcher = pattern.matcher(email);
        if(login.length() > 50 || login.length() < 1){
            formValues.replace(Literal.LOGIN_TO_ADD_IN_FORM,login,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        if(password.length() > 20 || password.length() < 8){
            formValues.replace(Literal.PASSWORD_TO_ADD_IN_FORM,password,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        if(!matcher.matches()){
            formValues.replace(Literal.EMAIL_TO_ADD_IN_FORM,email,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        return isValid;
    }

}
