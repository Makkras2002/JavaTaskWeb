package com.makkras.shop.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * The type Mail session factory.
 */
public class MailSessionFactory {
    private static MailSessionFactory instance;
    private MailSessionFactory(){
    }
    public static MailSessionFactory getInstance(){
        if(instance ==null){
            instance = new MailSessionFactory();
        }
        return instance;
    }
    public Session createSession(Properties properties){
        String userName = properties.getProperty("mail.user.name");
        String userPassword = properties.getProperty("mail.user.password");
        return Session.getDefaultInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,userPassword);
            }
        });
    }
}
