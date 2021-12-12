package com.makkras.shop.util.locale;

public class LocalizedTextExtractor {
    private static LocalizedTextExtractor instance;
    private LocalizedTextExtractor(){
    }
    public static LocalizedTextExtractor getInstance(){
        if(instance == null){
            instance =new LocalizedTextExtractor();
        }
        return instance;
    }
    public String getText(String currentLocale,String textKey){
        String text = null;
        if(currentLocale.equals("EN")){
            text = LocaleManager.EN.getString(textKey);
        }else {
            text = LocaleManager.RU.getString(textKey);
        }
        return text;
    }
}
