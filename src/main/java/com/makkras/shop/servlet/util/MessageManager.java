package com.makkras.shop.servlet.util;

import com.makkras.shop.exception.InteractionException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MessageManager {
    private static MessageManager instance;
    private MessageManager() {
    }
    public static MessageManager getInstance(){
        if(instance == null){
            instance = new MessageManager();
        }
        return instance;
    }
    public String getProperty(String key) throws InteractionException {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\foulder1.1\\Pam\\JavaTaskWeb\\datasrc\\message.properties"));
        } catch (IOException e) {
            throw new InteractionException(e.getMessage());
        }
        return properties.getProperty(key);
    }
}
