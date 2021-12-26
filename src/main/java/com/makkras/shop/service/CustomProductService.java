package com.makkras.shop.service;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface CustomProductService {
    List<Product> getAllProductsInStockFromDb() throws ServiceException;
    List<Product> getAllProductsInStockFromDbAndSortByName() throws ServiceException;
    List<Product> getAllProductsInStockFromDbAndSortByCategory() throws ServiceException;
    List<Product> getAllProductsInStockFromDbAndSortByPrice() throws ServiceException;
    List<Product> findProductsInStockFromDbByParams(String name, String category, BigDecimal min_price, BigDecimal max_price) throws ServiceException;
    List<ProductCategory> getAllProductCategoriesFromDb() throws ServiceException;
}
