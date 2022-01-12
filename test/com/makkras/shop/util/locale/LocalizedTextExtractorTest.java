package com.makkras.shop.util.locale;

import com.makkras.shop.controller.Literal;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LocalizedTextExtractorTest {
    private LocalizedTextExtractor localizedTextExtractor;
    private String currentLocale;
    private String textKey;
    private String expectedString;
    @BeforeClass
    public void setUp() {
        localizedTextExtractor = LocalizedTextExtractor.getInstance();
        currentLocale = Literal.DEFAULT_LOCALE;
        textKey = "USER_DELETE_ERROR";
        expectedString = "Этот пользователь в данный момент онлайн!!!";
    }

    @Test
    public void getTextTest() {
        assertEquals(localizedTextExtractor.getText(currentLocale,textKey),expectedString);
    }

}