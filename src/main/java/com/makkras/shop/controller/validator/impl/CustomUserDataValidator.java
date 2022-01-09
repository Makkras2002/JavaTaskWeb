package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.validator.UserDataValidator;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.util.locale.LocalizedTextExtractor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomUserDataValidator implements UserDataValidator {
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final int MIN_LOGIN_LENGTH = 1;
    private static final int MAX_LOGIN_LENGTH = 50;
    private static final int MAX_EMAIL_LENGTH = 50;
    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static CustomUserDataValidator instance;
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
        if(login.length() > MAX_LOGIN_LENGTH || login.length() < MIN_LOGIN_LENGTH){
            formValues.replace(Literal.LOGIN_TO_ADD_IN_FORM,login,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        if(password.length() > MAX_PASSWORD_LENGTH){
            formValues.replace(Literal.PASSWORD_TO_ADD_IN_FORM,password,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        return isValid;
    }
    public boolean validateUserChangeLoginData(String newLogin){
        boolean isValid = true;
        String login = newLogin;
        if(login.length() > MAX_LOGIN_LENGTH || login.length() < MIN_LOGIN_LENGTH){
            isValid = false;
        }
        return isValid;
    }
    public boolean validateUserChangePasswordData(String newPassword){
        boolean isValid = true;
        String password = newPassword;
        if(password.length() > MAX_PASSWORD_LENGTH || password.length() < MIN_PASSWORD_LENGTH){
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
        if(login.length() > MAX_LOGIN_LENGTH || login.length() < MIN_LOGIN_LENGTH){
            formValues.replace(Literal.LOGIN_TO_ADD_IN_FORM,login,
                    localizedTextExtractor.getText(locale,"INVALID_FORM_SYNTAX"));
            isValid = false;
        }
        if(password.length() > MAX_PASSWORD_LENGTH || password.length() < MIN_PASSWORD_LENGTH){
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

    public boolean validateUserSearchData(String rawLogin,String rawEmail) {
        boolean result = true;
        if(rawLogin.length() > MAX_LOGIN_LENGTH) {
            result = false;
        }
        if(rawEmail.length() > MAX_EMAIL_LENGTH) {
            result =false;
        }
        return result;
    }

}
