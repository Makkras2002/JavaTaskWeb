package com.makkras.shop.util.locale;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The enum Locale manager.
 */
public enum LocaleManager {
    EN(ResourceBundle.getBundle("localized_text",new Locale("en","GB"))),
    RU(ResourceBundle.getBundle("localized_text",new Locale("ru","RU")));
    private ResourceBundle bundle;
    LocaleManager(ResourceBundle bundle){
        this.bundle = bundle;
    }
    public String getString(String key){
        return bundle.getString(key);
    }
}
