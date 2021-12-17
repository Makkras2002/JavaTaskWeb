package com.makkras.shop.controller.util;

import com.makkras.shop.exception.ServiceException;

import java.util.ResourceBundle;

public class PathManager {
    private static PathManager instance;
    private PathManager() {
    }
    public static PathManager getInstance(){
        if(instance == null){
            instance = new PathManager();
        }
        return instance;
    }
    public String getProperty(String key) throws ServiceException {
        ResourceBundle properties = ResourceBundle.getBundle("datasrc.pagepath");
        return properties.getString(key);
    }
}

