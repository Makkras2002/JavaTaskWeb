package com.makkras.shop.service.impl;

import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.CustomProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ProductServiceTest {
    private static final String BLANK_CATEGORY_VALUE = "-";
    private static Logger logger  = LogManager.getLogger();

    @Mock
    private CustomProductService productServiceMock;
    private List<Product> allProductsFromDb;
    private List<Product> assertedProductsFinalList;
    private String name;
    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    @BeforeClass
    public void setUp() {
        name = "";
        category = "Category2";
        minPrice = BigDecimal.valueOf(0);
        maxPrice = BigDecimal.valueOf(10000);
        productServiceMock = Mockito.mock(ProductService.class);
        allProductsFromDb = List.of(new Product((long)1,"Product1",BigDecimal.valueOf(111),true,"picturePath1","Comment1",new ProductCategory("Category1")),
                new Product((long)2,"Product2",BigDecimal.valueOf(222),true,"picturePath2","Comment2",new ProductCategory("Category2")),
                new Product((long)3,"Product3",BigDecimal.valueOf(333),true,"picturePath3","Comment3",new ProductCategory("Category1")),
                new Product((long)4,"Product4",BigDecimal.valueOf(444),true,"picturePath4","Comment4",new ProductCategory("Category2")));
        assertedProductsFinalList = List.of(new Product((long)2,"Product2",BigDecimal.valueOf(222),true,"picturePath2","Comment2",new ProductCategory("Category2")),
                new Product((long)4,"Product4",BigDecimal.valueOf(444),true,"picturePath4","Comment4",new ProductCategory("Category2")));
        try {
            Mockito.when(productServiceMock.findAllProductsInStockFromDb()).thenReturn(allProductsFromDb);
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void findProductsInStockFromDbByParamsTest() throws ServiceException {
        List<Product> allProducts = productServiceMock.findAllProductsInStockFromDb();
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
        assertEquals(requiredProducts,assertedProductsFinalList);

    }

    @AfterClass
    public void after() {
        try {
            Mockito.verify(productServiceMock, Mockito.times(1)).findAllProductsInStockFromDb();
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
    }
}