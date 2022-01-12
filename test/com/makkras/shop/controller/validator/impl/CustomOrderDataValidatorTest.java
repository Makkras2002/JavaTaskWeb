package com.makkras.shop.controller.validator.impl;

import com.makkras.shop.controller.validator.OrderDataValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomOrderDataValidatorTest {
    private OrderDataValidator orderDataValidator;
    private String rawLogin;
    private String rawStartDate;
    private String rawEndDate;

    @BeforeClass
    public void setUp() {
        orderDataValidator = CustomOrderDataValidator.getInstance();
        rawLogin = "Vincent";
        rawStartDate = "2005-05-05";
        rawEndDate = "2010-10-01";
    }

    @Test
    public void validateOrderSearchDataTest() {

        assertTrue(orderDataValidator.validateOrderSearchData(rawLogin,rawStartDate,rawEndDate));
    }
}