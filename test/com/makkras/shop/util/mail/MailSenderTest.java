package com.makkras.shop.util.mail;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MailSenderTest {
    private MailSender mailSender;
    private String destinationEmail;
    private String messageSubject;
    private String messageText;
    @BeforeClass
    public void setUp() {
        mailSender = MailSender.getInstance();
        destinationEmail = null;
        messageSubject = "Important subject";
        messageText = "Important text";
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void sendExceptionTest() {
        mailSender.send(destinationEmail,messageSubject,messageText);
    }
}