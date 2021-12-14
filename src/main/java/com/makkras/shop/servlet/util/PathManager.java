package com.makkras.shop.servlet.util;

import com.makkras.shop.exception.InteractionException;
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
    public String getProperty(String key) throws InteractionException {
        ResourceBundle properties = ResourceBundle.getBundle("datasrc.pagepath");
        return properties.getString(key);
    }
}

