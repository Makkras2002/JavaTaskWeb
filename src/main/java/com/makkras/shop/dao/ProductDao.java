package com.makkras.shop.dao;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.InteractionException;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Product dao.
 */
public interface ProductDao extends BaseDao<Product>{
    /**
     * Find products by name and collect them in the list.
     *
     * @param name the name
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findProductsByName(String name) throws InteractionException;

    /**
     * Find product by id and collect them in the list.
     *
     * @param id the id
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findProductById(Long id) throws InteractionException;

    /**
     * Find products by product category and collect them in the list.
     *
     * @param productCategory the product category
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findProductsByProductCategory(ProductCategory productCategory) throws InteractionException;

    /**
     * Find all product in stock and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findAllProductInStock() throws InteractionException;

    /**
     * Find all product in stock and sort by name, and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findAllProductInStockAndSortByName() throws InteractionException;

    /**
     * Find all product in stock and sort by category, and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findAllProductInStockAndSortByCategory() throws InteractionException;

    /**
     * Find all product in stock and sort by price, and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findAllProductInStockAndSortByPrice() throws InteractionException;

    /**
     * Find products by price in range and sort, and collect them in the list.
     *
     * @param minPrice the min price
     * @param maxPrice the max price
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findProductsByPriceInRangeAndSort(BigDecimal minPrice,BigDecimal maxPrice) throws InteractionException;

    /**
     * Update product name and return boolean true if operation was successful.
     *
     * @param newProductName the new product name
     * @param productId      the product id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateProductName(String newProductName, Long productId) throws InteractionException;

    /**
     * Update product category and return boolean true if operation was successful.
     *
     * @param newProductCategory the new product category
     * @param productId          the product id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateProductCategory(String newProductCategory, Long productId) throws InteractionException;

    /**
     * Update is in stock status and return boolean true if operation was successful.
     *
     * @param newIsInStockStatus the new is in stock status
     * @param productId          the product id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateIsInStockStatus(boolean newIsInStockStatus, Long productId) throws InteractionException;

    /**
     * Update price and return boolean true if operation was successful.
     *
     * @param newPrice  the new price
     * @param productId the product id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updatePrice(BigDecimal newPrice, Long productId) throws InteractionException;

    /**
     * Update picture path and return boolean true if operation was successful.
     *
     * @param newPicturePath the new picture path
     * @param productId      the product id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updatePicturePath(String newPicturePath, Long productId) throws InteractionException;

    /**
     * Update comment and return boolean true if operation was successful.
     *
     * @param newComment the new comment
     * @param productId  the product id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateComment(String newComment, Long productId) throws InteractionException;

    /**
     * Find all product out of stock and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<Product> findAllProductOutOfStock() throws InteractionException;
}
