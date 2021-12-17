package com.makkras.shop.controller.util;

import com.makkras.shop.exception.ServiceException;

import java.util.ResourceBundle;

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
    public String getProperty(String key) throws ServiceException {
        ResourceBundle properties = ResourceBundle.getBundle("datasrc.message");
        return properties.getString(key);
    }
}
