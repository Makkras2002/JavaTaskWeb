package com.makkras.shop.util.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleManager {
    EN(ResourceBundle.getBundle("text1",new Locale("en","GB"))),
    RU(ResourceBundle.getBundle("text1"));
    private ResourceBundle bundle;
    LocaleManager(ResourceBundle bundle){
        this.bundle = bundle;
    }
    public String getString(String key){
        return bundle.getString(key);
    }
}
