package com.makkras.shop.service;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomProductService {
    List<Product> findAllProductsInStockFromDb() throws ServiceException;
    List<Product> findAllProductsInStockFromDbAndSortByName() throws ServiceException;
    List<Product> findAllProductsInStockFromDbAndSortByCategory() throws ServiceException;
    List<Product> findAllProductsInStockFromDbAndSortByPrice() throws ServiceException;
    List<Product> findProductsInStockFromDbByParams(String name, String category, BigDecimal minPrice, BigDecimal maxPrice) throws ServiceException;
    List<ProductCategory> findAllProductCategoriesFromDb() throws ServiceException;
    Optional<Product> findProductById(Long productId) throws ServiceException;
    List<Product> findAllProductsFromDb() throws ServiceException;
    Optional<Product> findProductByName(String name) throws ServiceException;
    boolean addProductToDb(Product product) throws ServiceException;
    boolean updateChangedFieldsInProduct(Product product) throws ServiceException;
    List<Product> findAllOutOfStockProductsFromDb() throws ServiceException;
}
