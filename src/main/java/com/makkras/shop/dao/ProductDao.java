package com.makkras.shop.dao;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.InteractionException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao extends BaseDao<Product>{
    List<Product> findProductsByName(String name) throws InteractionException;
    List<Product> findProductById(Long id) throws InteractionException;
    List<Product> findProductsByProductCategory(ProductCategory productCategory) throws InteractionException;
    List<Product> findAllProductInStock() throws InteractionException;
    List<Product> findAllProductInStockAndSortByName() throws InteractionException;
    List<Product> findAllProductInStockAndSortByCategory() throws InteractionException;
    List<Product> findAllProductInStockAndSortByPrice() throws InteractionException;
    List<Product> findProductsByPriceInRangeAndSort(BigDecimal minPrice,BigDecimal maxPrice) throws InteractionException;
    boolean updateProductName(String newProductName, Long productId) throws InteractionException;
    boolean updateProductCategory(String newProductCategory, Long productId) throws InteractionException;
    boolean updateIsInStockStatus(boolean newIsInStockStatus, Long productId) throws InteractionException;
    boolean updatePrice(BigDecimal newPrice, Long productId) throws InteractionException;
    boolean updatePicturePath(String newPicturePath, Long productId) throws InteractionException;
    boolean updateComment(String newComment, Long productId) throws InteractionException;
    List<Product> findAllProductOutOfStock() throws InteractionException;
}
