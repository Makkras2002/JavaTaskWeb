package com.makkras.shop.controller.validator;

import jakarta.servlet.http.HttpServletRequest;

public interface ProductDataValidator {
    boolean validateProductSearchData(HttpServletRequest request);
}
