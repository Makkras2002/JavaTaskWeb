package com.makkras.shop.dao;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;

import java.util.List;

public interface ProductDao extends BaseDao<Product>{
    List<Product> findProductsByName(String name);
    List<Product> findProductsByProductCategory(ProductCategory productCategory);
    List<Product> findAllProductInStock();
    List<Product> findAllProductInStockAndSortByPrice();
}
