package com.makkras.shop.controller.util;

public class Literal {
    public static final String LOGIN_NAME = "login";
    public static final String PASSWORD = "password";
    public static final String ENTER_AS_ADMIN = "enterAsAdmin";
    public static final String DEFAULT_LOCALE = "RU";
    public static final String EMAIL = "email";
    public static final String AUTHORIZATION_ERROR_MESSAGE = "errorAuthMessage";
    public static final String ORDER = "order";
    public static final String LOCALE_NAME = "locale";
    public static final String ERROR_REGISTRATION_MESSAGE = "errorRegistrationMessage";
    public static final String COMMAND = "command";
    public static final String SUCCESSFUL_REG_EMAIL_BODY = """
            <html><body><p>Congratulations on successful registration on Web-Shop \"AutoShop\". Please, follow the link to confirm registration. </p></br> 
            <a href=\" http://localhost:8888/pages/confirm_registration.jsp\">Confirm registration</a></body></html>""";
    public static final String SUCCESSFUL_REG_EMAIL_HEADER = "Web-Shop \"AutoShop\"";
    public static final String REGISTRATION_PAGE = "/pages/registration.jsp";
    public static final String AUTHORIZATION_PAGE = "/pages/authorization.jsp";
    public static final String MAIN_CLIENT_PAGE = "/index.jsp";
    public static final String RAW_MAIN_CLIENT_PAGE = "/pages/mainclient.jsp";
    public static final String CONFIRM_REGISTRATION_PAGE = "/pages/confirm_registration.jsp";
    public static final String LOGIN_ERROR = "Invalid Login or Password!!!";
    public static final String REGISTRATION_ERROR = "User with such email or login already exists!!!";
    public static final String REGISTRATION_ERROR_V2 = "User wasn't registered!!!";
    public static final String REGISTRATION_CONFIRMATION_AWAIT = "Please, confirm your registration by following link on your email.";
    public static final String CONFIRMATION_REGISTRATION_ERROR = "Your confirmation of registration time has expired. Please register again.";
    public static final String CONFIRMATION_REGISTRATION_ERROR_V2 = "You have already confirmed your registration.";
    public static final String COMMAND_ERROR = "Unknown command delivered to Servlet.";
    public static final String FIRST_LOGOUT_ERROR = "First logout from your current account to enter or register as new user!!!";
    public static final String PRODUCTS_IN_STOCK = "productsInStock";
}
