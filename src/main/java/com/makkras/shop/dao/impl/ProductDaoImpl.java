package com.makkras.shop.dao.impl;

import com.makkras.shop.dao.ProductCategoryDao;
import com.makkras.shop.dao.ProductDao;
import com.makkras.shop.entity.Product;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static Logger logger = LogManager.getLogger();


    private static final String SQL_SELECT_ALL_PRODUCTS = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id";
    private static final String SQL_SELECT_PRODUCTS_WITH_NAME = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id WHERE products.product_name = ?";
    private static final String SQL_CREATE_PRODUCT= "INSERT INTO products (product_name,product_price,is_in_stock,picture_path," +
            "product_comment,product_category_id)" +
            " VALUES (?,?,?,?,?,?)";
    private static final String SQL_SELECT_PRODUCTS_WITH_CATEGORY = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id WHERE product_categories.category = ?";
    private static final String SQL_SELECT_PRODUCTS_IN_STOCK = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id WHERE products.is_in_stock = true";
    private static final String SQL_SELECT_PRODUCTS_IN_STOCK_AND_SORT_BY_NAME = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id WHERE products.is_in_stock = true ORDER BY products.product_name ASC";
    private static final String SQL_SELECT_PRODUCTS_IN_STOCK_AND_SORT_BY_CATEGORY = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id WHERE products.is_in_stock = true ORDER BY product_categories.category ASC";
    private static final String SQL_SELECT_PRODUCTS_IN_STOCK_AND_SORT_BY_PRICE = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id WHERE products.is_in_stock = true ORDER BY products.product_price ASC";
    private static final String SQL_SELECT_PRODUCTS_IN_STOCK_IN_PRICE_RANGE_AND_SORT_BY_PRICE = "SELECT products.product_id, products.product_name, " +
            "products.product_price, products.is_in_stock," +
            " products.picture_path, products.product_comment, products.product_category_id, product_categories.category FROM products JOIN product_categories " +
            "ON products.product_category_id = product_categories.category_id WHERE products.is_in_stock = true AND products.product_price > ? AND products.product_price < ? ORDER BY products.product_price ASC";
    private static final String SQL_UPDATE_PRODUCT_NAME= "UPDATE products SET product_name  = ? WHERE product_id = ?";
    private static final String SQL_UPDATE_PRODUCT_CATEGORY= "UPDATE products SET product_category_id  = ? WHERE product_id = ?";
    private static final String SQL_UPDATE_PRODUCT_IS_IN_STOCK_STATUS= "UPDATE products SET is_in_stock  = ? WHERE product_id = ?";
    private static final String SQL_UPDATE_PRODUCT_COMMENT= "UPDATE products SET product_comment  = ? WHERE product_id = ?";
    private static final String SQL_UPDATE_PICTURE_PATH= "UPDATE products SET picture_path  = ? WHERE product_id = ?";
    private static final String SQL_UPDATE_PRODUCT_PRICE= "UPDATE products SET product_price  = ? WHERE product_id = ?";

    public ProductDaoImpl(){
    }
    @Override
    public List<Product> findAll() throws InteractionException {
        List<Product> products = selectDataFromDbByQuery(SQL_SELECT_ALL_PRODUCTS);
        return products;
    }

    @Override
    public Long create(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        Long createdItemKey = null;
        try {
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl();
            Long productCategoryKey = null;
            List<ProductCategory> categories = productCategoryDao.findProductCategoryByName(product.getProductCategory().getCategory());
            if(categories.size() ==0){
                productCategoryKey = productCategoryDao.create(product.getProductCategory());
            }else {
                productCategoryKey = categories.get(0).getCategoryId();
            }
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_PRODUCT,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getProductName());
            statement.setBigDecimal(2,product.getProductPrice());
            statement.setBoolean(3,product.isInStock());
            statement.setString(4,product.getPicturePath());
            statement.setString(5,product.getProductComment());
            statement.setLong(6,productCategoryKey);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                createdItemKey = resultSet.getLong(1);
            }
        } catch (SQLException | InteractionException exception) {
            logger.error(exception.getMessage());

        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return createdItemKey;
    }

    @Override
    public List<Product> findProductsByName(String name) throws InteractionException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_PRODUCTS_WITH_NAME);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long productId = resultSet.getLong(1);
                String productName = resultSet.getString(2);
                BigDecimal productPrice = resultSet.getBigDecimal(3);
                Boolean isInStock = resultSet.getBoolean(4);
                String picturePath = resultSet.getString(5);
                String productComment = resultSet.getString(6);
                ProductCategory category = new ProductCategory(resultSet.getLong(7),resultSet.getString(8));
                products.add(new Product(productId,productName,productPrice,isInStock,picturePath,productComment,category));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return products;
    }

    @Override
    public List<Product> findProductsByProductCategory(ProductCategory productCategory) throws InteractionException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_PRODUCTS_WITH_CATEGORY);
            statement.setString(1,productCategory.getCategory());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long productId = resultSet.getLong(1);
                String productName = resultSet.getString(2);
                BigDecimal productPrice = resultSet.getBigDecimal(3);
                Boolean isInStock = resultSet.getBoolean(4);
                String picturePath = resultSet.getString(5);
                String productComment = resultSet.getString(6);
                ProductCategory category = new ProductCategory(resultSet.getLong(7),resultSet.getString(8));
                products.add(new Product(productId,productName,productPrice,isInStock,picturePath,productComment,category));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return products;
    }

    @Override
    public List<Product> findAllProductInStock() throws InteractionException {
        List<Product> products = selectDataFromDbByQuery(SQL_SELECT_PRODUCTS_IN_STOCK);
        return products;
    }

    @Override
    public List<Product> findAllProductInStockAndSortByName() throws InteractionException {
        List<Product> products = selectDataFromDbByQuery(SQL_SELECT_PRODUCTS_IN_STOCK_AND_SORT_BY_NAME);
        return products;
    }

    @Override
    public List<Product> findAllProductInStockAndSortByCategory() throws InteractionException {
        List<Product> products = selectDataFromDbByQuery(SQL_SELECT_PRODUCTS_IN_STOCK_AND_SORT_BY_CATEGORY);
        return products;
    }

    @Override
    public List<Product> findAllProductInStockAndSortByPrice() throws InteractionException {
        List<Product> products = selectDataFromDbByQuery(SQL_SELECT_PRODUCTS_IN_STOCK_AND_SORT_BY_PRICE);
        return products;
    }
    @Override
    public List<Product> findProductsByPriceInRangeAndSort(BigDecimal minPrice, BigDecimal maxPrice) throws InteractionException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_PRODUCTS_IN_STOCK_IN_PRICE_RANGE_AND_SORT_BY_PRICE);
            statement.setBigDecimal(1,minPrice);
            statement.setBigDecimal(2,maxPrice);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long productId = resultSet.getLong(1);
                String productName = resultSet.getString(2);
                BigDecimal productPrice = resultSet.getBigDecimal(3);
                Boolean isInStock = resultSet.getBoolean(4);
                String picturePath = resultSet.getString(5);
                String productComment = resultSet.getString(6);
                ProductCategory category = new ProductCategory(resultSet.getLong(7),resultSet.getString(8));
                products.add(new Product(productId,productName,productPrice,isInStock,picturePath,productComment,category));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return products;
    }

    @Override
    public boolean updateProductName(String newProductName, Long productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_PRODUCT_NAME);
            statement.setString(1,newProductName);
            statement.setLong(2,productId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            return false;
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updateProductCategory(String newProductCategory, Long productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        Long newCategoryId = null;
        try {
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl();
            List<ProductCategory> categories = productCategoryDao.findProductCategoryByName(newProductCategory);
            if(categories.size() == 0){
                return false;
            }else {
                newCategoryId = categories.get(0).getCategoryId();
            }
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_PRODUCT_CATEGORY);
            statement.setLong(1,newCategoryId);
            statement.setLong(2,productId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException | InteractionException exception) {
            logger.error(exception.getMessage());
            return false;
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updateIsInStockStatus(boolean newIsInStockStatus, Long productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_PRODUCT_IS_IN_STOCK_STATUS);
            statement.setBoolean(1,newIsInStockStatus);
            statement.setLong(2,productId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            return false;
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updatePrice(BigDecimal newPrice, Long productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_PRODUCT_PRICE);
            statement.setBigDecimal(1,newPrice);
            statement.setLong(2,productId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            return false;
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updatePicturePath(String newPicturePath, Long productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_PICTURE_PATH);
            statement.setString(1,newPicturePath);
            statement.setLong(2,productId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            return false;
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean updateComment(String newComment, Long productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_PRODUCT_COMMENT);
            statement.setString(1,newComment);
            statement.setLong(2,productId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
            return false;
        } finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    private List<Product> selectDataFromDbByQuery(String query) throws InteractionException {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long productId = resultSet.getLong(1);
                String productName = resultSet.getString(2);
                BigDecimal productPrice = resultSet.getBigDecimal(3);
                Boolean isInStock = resultSet.getBoolean(4);
                String picturePath = resultSet.getString(5);
                String productComment = resultSet.getString(6);
                ProductCategory category = new ProductCategory(resultSet.getLong(7),resultSet.getString(8));
                products.add(new Product(productId,productName,productPrice,isInStock,picturePath,productComment,category));
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }finally {
            try {
                closeStatement(statement);
                closeConnection(connection);
            } catch (InteractionException e) {
                logger.error(e.getMessage());
            }
        }
        return products;
    }
}