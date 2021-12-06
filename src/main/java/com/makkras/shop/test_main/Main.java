package com.makkras.shop.test_main;

import com.makkras.shop.dao.OrderDao;
import com.makkras.shop.dao.ProductCategoryDao;
import com.makkras.shop.dao.ProductDao;
import com.makkras.shop.dao.UserDao;
import com.makkras.shop.dao.impl.OrderDaoImpl;
import com.makkras.shop.dao.impl.ProductCategoryDaoImpl;
import com.makkras.shop.dao.impl.ProductDaoImpl;
import com.makkras.shop.dao.impl.UserDaoImpl;
import com.makkras.shop.entity.*;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        try {
            List<User> userList = userDao.findAll();
            for (User p1: userList){
                logger.info(p1);
            }
            logger.info("____________________________");
//            logger.info(productDao.create(new Product("Колесный тормозной цилиндр", BigDecimal.valueOf(40.75),true,
//                    "C:\\foulder1.1\\Pam\\JavaTaskWeb\\picture\\brake_cilin.png","Колесный тормозной цилиндр трансформирует давление жидкости в механическую силу, которая действует на тормозные колодки. В результате происходит торможение или полная остановка транспортного средства.",
//                    new ProductCategory("Колесные"))));
//            List<Product> products = productDao.findAllProductInStockAndSortByCategory();
//            for (Product p: products){
//                logger.info(p);
//            }
//            List<ComponentOrder> orders1 = new ArrayList<>();
//            orders1.add(new ComponentOrder(new Product((long)0),(long)4,BigDecimal.valueOf(1200)));
//            orders1.add(new ComponentOrder(new Product((long)6),(long)1,BigDecimal.valueOf(5.9)));
//            logger.info(orderDao.create(new CompleteOrder(Date.valueOf("2021-12-07"),new User((long)1,"Papa","Shpak70Ig@mail.ru",true),true,orders1)));
            List<CompleteOrder> orders = orderDao.findAll();
            for (CompleteOrder p1: orders){
                logger.info(p1);
                logger.info("!!");
            }
            CustomConnectionPool.getInstance().destroyPool();
        } catch (InteractionException e) {
            logger.error(e.getMessage());
        }
    }
}
