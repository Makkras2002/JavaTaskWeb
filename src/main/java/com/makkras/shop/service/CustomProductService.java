package com.makkras.shop.service;

import com.makkras.shop.entity.Product;
import com.makkras.shop.exception.ServiceException;

import java.util.List;

public interface CustomProductService {
    List<Product> getAllProductsInStockFromDb() throws ServiceException;
}
