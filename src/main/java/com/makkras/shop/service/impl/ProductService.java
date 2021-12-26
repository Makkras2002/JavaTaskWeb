package com.makkras.shop.service.impl;

import com.makkras.shop.dao.ProductCategoryDao;
import com.makkras.shop.dao.ProductDao;
import com.makkras.shop.dao.impl.ProductCategoryDaoImpl;
import com.makkras.shop.dao.impl.ProductDaoImpl;
import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.CustomProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements CustomProductService {
    private static ProductService instance;
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private ProductService(){
        productDao = new ProductDaoImpl();
        productCategoryDao = new ProductCategoryDaoImpl();
    }
    public static ProductService getInstance(){
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }
    public List<Product> getAllProductsInStockFromDb() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStock();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<ProductCategory> getAllProductCategoriesFromDb() throws ServiceException {
        List<ProductCategory> productCategories = new ArrayList<>();
        try {
            productCategories = productCategoryDao.findAll();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return productCategories;
    }
    public List<Product> getAllProductsInStockFromDbAndSortByName() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStockAndSortByName();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<Product> getAllProductsInStockFromDbAndSortByCategory() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStockAndSortByCategory();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<Product> getAllProductsInStockFromDbAndSortByPrice() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStockAndSortByPrice();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<Product> findProductsInStockFromDbByParams(String name, String category, BigDecimal min_price, BigDecimal max_price) throws ServiceException {
        List<Product> allProducts = getAllProductsInStockFromDb();
        List<Product> requiredProducts = new ArrayList<>();
        int counter = 0;
        while (counter < allProducts.size()){
            if(((allProducts.get(counter).getProductName().contains(name) && name.length()>3) || name.equals("")) &&
                    (allProducts.get(counter).getProductCategory().getCategory().equals(category) || category.equals("-")) &&
                    (allProducts.get(counter).getProductPrice().compareTo(min_price) > -1 || min_price.compareTo(BigDecimal.valueOf(0)) == 0) && (allProducts.get(counter).getProductPrice().compareTo(max_price) < 1 || max_price.compareTo(BigDecimal.valueOf(0)) == 0)) {
                requiredProducts.add(allProducts.get(counter));
            }
            counter++;
        }
        return requiredProducts;
    }
}
