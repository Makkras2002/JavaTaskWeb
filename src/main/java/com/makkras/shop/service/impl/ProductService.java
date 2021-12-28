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
    private static final String BLANK_CATEGORY_VALUE = "-";
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
    public List<Product> findAllProductsInStockFromDb() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStock();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<ProductCategory> findAllProductCategoriesFromDb() throws ServiceException {
        List<ProductCategory> productCategories = new ArrayList<>();
        try {
            productCategories = productCategoryDao.findAll();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return productCategories;
    }
    public List<Product> findAllProductsInStockFromDbAndSortByName() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStockAndSortByName();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<Product> findAllProductsInStockFromDbAndSortByCategory() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStockAndSortByCategory();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<Product> findAllProductsInStockFromDbAndSortByPrice() throws ServiceException {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.findAllProductInStockAndSortByPrice();
        } catch (InteractionException e) {
            throw new ServiceException(e.getMessage());
        }
        return products;
    }
    public List<Product> findProductsInStockFromDbByParams(String name, String category, BigDecimal minPrice, BigDecimal maxPrice) throws ServiceException {
        List<Product> allProducts = findAllProductsInStockFromDb();
        List<Product> requiredProducts = new ArrayList<>();
        int counter = 0;
        while (counter < allProducts.size()){
            if(((allProducts.get(counter).getProductName().contains(name) && name.length()>3) || name.isBlank()) &&
                    (allProducts.get(counter).getProductCategory().getCategory().equals(category) || category.equals(BLANK_CATEGORY_VALUE)) &&
                    (allProducts.get(counter).getProductPrice().compareTo(minPrice) > -1 || minPrice.compareTo(BigDecimal.valueOf(0)) == 0) && (allProducts.get(counter).getProductPrice().compareTo(maxPrice) < 1 || maxPrice.compareTo(BigDecimal.valueOf(0)) == 0)) {
                requiredProducts.add(allProducts.get(counter));
            }
            counter++;
        }
        return requiredProducts;
    }
}
