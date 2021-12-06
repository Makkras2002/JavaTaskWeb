package com.makkras.shop.dao;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.InteractionException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao extends BaseDao<Product>{
    List<Product> findProductsByName(String name) throws InteractionException;
    List<Product> findProductsByProductCategory(ProductCategory productCategory) throws InteractionException;
    List<Product> findAllProductInStock() throws InteractionException;
    List<Product> findAllProductInStockAndSortByName() throws InteractionException;
    List<Product> findAllProductInStockAndSortByCategory() throws InteractionException;
    List<Product> findAllProductInStockAndSortByPrice() throws InteractionException;
    List<Product> findProductsByPriceInRangeAndSort(BigDecimal minPrice,BigDecimal maxPrice) throws InteractionException;
    boolean updateProductName(String newProductName, Long productId);
    boolean updateProductCategory(String newProductCategory, Long productId);
    boolean updateIsInStockStatus(boolean newIsInStockStatus, Long productId);
    boolean updatePrice(BigDecimal newPrice, Long productId);
    boolean updatePicturePath(String newPicturePath, Long productId);
    boolean updateComment(String newComment, Long productId);
}
