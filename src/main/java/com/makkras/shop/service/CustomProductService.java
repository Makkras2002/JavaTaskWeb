package com.makkras.shop.service;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomProductService {
    /**
     * Find all products in stock from db and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllProductsInStockFromDb() throws ServiceException;

    /**
     * Find all products in stock from db and sort by name and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllProductsInStockFromDbAndSortByName() throws ServiceException;

    /**
     * Find all products in stock from db and sort by category and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllProductsInStockFromDbAndSortByCategory() throws ServiceException;

    /**
     * Find all products in stock from db and sort by price and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllProductsInStockFromDbAndSortByPrice() throws ServiceException;

    /**
     * Find products in stock from db by params and collect them in the list.
     *
     * @param name     the name
     * @param category the category
     * @param minPrice the min price
     * @param maxPrice the max price
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findProductsInStockFromDbByParams(String name, String category, BigDecimal minPrice, BigDecimal maxPrice) throws ServiceException;

    /**
     * Find all product categories from db and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProductCategory> findAllProductCategoriesFromDb() throws ServiceException;

    /**
     * Find product by id and return optional result.
     *
     * @param productId the product id
     * @return the optional result
     * @throws ServiceException the service exception
     */
    Optional<Product> findProductById(Long productId) throws ServiceException;

    /**
     * Find all products from db and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllProductsFromDb() throws ServiceException;

    /**
     * Find product by name and return optional result.
     *
     * @param name the name
     * @return the optional result
     * @throws ServiceException the service exception
     */
    Optional<Product> findProductByName(String name) throws ServiceException;

    /**
     * Add product to db and return boolean result if product was successfully added.
     *
     * @param product the product
     * @return the boolean success result
     * @throws ServiceException the service exception
     */
    boolean addProductToDb(Product product) throws ServiceException;

    /**
     * Update changed fields in product and return boolean result if fields were successfully changed.
     *
     * @param product the product
     * @return the boolean success result
     * @throws ServiceException the service exception
     */
    boolean updateChangedFieldsInProduct(Product product) throws ServiceException;

    /**
     * Find all out of stock products from db and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> findAllOutOfStockProductsFromDb() throws ServiceException;
}
