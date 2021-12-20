package com.makkras.shop.service;

import com.makkras.shop.dao.ProductDao;
import com.makkras.shop.dao.impl.ProductDaoImpl;
import com.makkras.shop.entity.Product;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService instance;
    private ProductDao productDao;
    private ProductService(){
        productDao = new ProductDaoImpl();
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

}
