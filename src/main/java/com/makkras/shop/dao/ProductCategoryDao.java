package com.makkras.shop.dao;

import com.makkras.shop.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao extends BaseDao<ProductCategory>{
    List<ProductCategory> findProductCategoryByName(String name);
}
