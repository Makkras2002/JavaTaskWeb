package com.makkras.shop.dao.impl;

import com.makkras.shop.dao.ProductCategoryDao;
import com.makkras.shop.entity.ProductCategory;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoImpl implements ProductCategoryDao {
    private static Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_CATEGORIES = "SELECT category_id, category" +
            " FROM product_categories";
    private static final String SQL_CREATE_CATEGORY= "INSERT INTO product_categories (category)" +
            " VALUES (?)";
    private static final String SQL_SELECT_CATEGORIES_WITH_CATEGORY_NAME = "SELECT category_id, category" +
            " FROM product_categories WHERE category = ?";
    private static final String SQL_UPDATE_CATEGORY_NAME = "UPDATE product_categories" +
            " SET category = ? WHERE category = ?";

    public ProductCategoryDaoImpl(){
    }
    @Override
    public List<ProductCategory> findAll() throws InteractionException {
        List<ProductCategory> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_CATEGORIES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long categoryId = resultSet.getLong(1);
                String category = resultSet.getString(2);
                categories.add(new ProductCategory(categoryId,category));
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
        return categories;
    }

    @Override
    public Long create(ProductCategory productCategory) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        Long createdItemKey = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_CATEGORY,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, productCategory.getCategory());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                createdItemKey = resultSet.getLong(1);
            }
        } catch (SQLException exception) {
            throw  new InteractionException(exception.getMessage());
        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return createdItemKey;
    }

    @Override
    public List<ProductCategory> findProductCategoryByName(String name) throws InteractionException {
        List<ProductCategory> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_CATEGORIES_WITH_CATEGORY_NAME);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long categoryId = resultSet.getLong(1);
                String category = resultSet.getString(2);
                categories.add(new ProductCategory(categoryId,category));
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
        return categories;
    }

    @Override
    public boolean updateCategoryName(String newCategoryName, String oldCategoryName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_CATEGORY_NAME);
            statement.setString(1,newCategoryName);
            statement.setString(2,oldCategoryName);
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
}
