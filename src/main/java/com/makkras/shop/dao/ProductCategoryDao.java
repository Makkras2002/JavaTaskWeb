package com.makkras.shop.dao;

import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.InteractionException;

import java.util.List;

public interface ProductCategoryDao extends BaseDao<ProductCategory>{
    List<ProductCategory> findProductCategoryByName(String name) throws InteractionException;
    boolean updateCategoryName(String newCategoryName, String oldCategoryName) throws InteractionException;
}
