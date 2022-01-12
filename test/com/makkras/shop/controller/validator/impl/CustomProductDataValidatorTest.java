package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.validator.ProductDataValidator;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CustomProductDataValidatorTest {
    private ProductDataValidator productDataValidator;
    private String rawName;
    private String rawCategory;
    private String rawMinPrice;
    private String rawMaxPrice;
    private Map<String,String> formValues;
    private LocalizedTextExtractor localizedTextExtractor;

    @BeforeClass
    public void setUp() {
        productDataValidator = CustomProductDataValidator.getInstance();
        localizedTextExtractor = LocalizedTextExtractor.getInstance();
        rawName = "АвтоЗапчасть";
        rawCategory = "Моторные";
        rawMinPrice = "100";
        rawMaxPrice = "200b";
        formValues = new HashMap<>();
        formValues.put(Literal.PRODUCT_NAME,rawName);
        formValues.put(Literal.PRODUCT_PRICE,"144.bg5");
        formValues.put(Literal.PRODUCT_CATEGORY,rawCategory);
        formValues.put(Literal.PRODUCT_IMAGE_PATH,"");
        formValues.put(Literal.PRODUCT_COMMENT,"ayf");
    }

    @Test
    public void validateProductSearchDataTest() {
        assertFalse(productDataValidator.validateProductSearchData(rawName,rawCategory,rawMinPrice,rawMaxPrice));
    }

    @Test
    public void validateProductAddDataTest(){
        productDataValidator.validateProductAddData(formValues,Literal.BRITISH_LOCALE);
        assertEquals(formValues.get(Literal.PRODUCT_PRICE),
                localizedTextExtractor.getText(Literal.BRITISH_LOCALE,"INVALID_FORM_SYNTAX"));
    }
}