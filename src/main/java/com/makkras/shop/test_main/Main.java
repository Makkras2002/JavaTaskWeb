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
import com.makkras.shop.util.locale.LocaleManager;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        try {
            List<User> userList = userDao.findAllUsersWithRole(UserRole.ADMIN);
            for (User p1: userList){
                logger.info(p1);
            }
            logger.info("____________________________");
//            logger.info(productDao.create(new Product("Колесный тормозной цилиндр", BigDecimal.valueOf(40.75),true,
//                    "C:\\foulder1.1\\Pam\\JavaTaskWeb\\picture\\brake_cilin.png","Колесный тормозной цилиндр трансформирует давление жидкости в механическую силу, которая действует на тормозные колодки. В результате происходит торможение или полная остановка транспортного средства.",
//                    new ProductCategory("Колесные"))));
            List<Product> products = productDao.findAllProductInStockAndSortByName();
            for (Product p: products){
                logger.info(p);
            }
//            List<ComponentOrder> orders1 = new ArrayList<>();
//            orders1.add(new ComponentOrder(new Product((long)7),(long)10,BigDecimal.valueOf(305)));
//            orders1.add(new ComponentOrder(new Product((long)8),(long)14,BigDecimal.valueOf(570.5)));
//            logger.info(orderDao.create(new CompleteOrder(Date.valueOf("2021-12-10"),new User((long)4,"Dmitri","dimas2000alpha@gmail.com",true),false,orders1)));
//            orderDao.updateCompleteOrderDate(Date.valueOf("2021-10-10"),(long) 6);
//            List<CompleteOrder> orders = orderDao.findAllCompletedOrdersAndSortByDate();
//            for (CompleteOrder p1: orders){
//                logger.info(p1);
//                logger.info("!!");
//            }
//            MailSender.getInstance().send("max2002shpak.com","Sample Mail!!!","Hello!!!");
            logger.info(LocalizedTextExtractor.getInstance().getText("RU","str1"));
            logger.info(LocalizedTextExtractor.getInstance().getText("EN","str1"));
            CustomConnectionPool.getInstance().destroyPool();
        } catch (InteractionException e) {
            logger.error(e.getMessage());
        }
    }
}
