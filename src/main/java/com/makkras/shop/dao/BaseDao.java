package com.makkras.shop.dao;

import com.makkras.shop.entity.CustomEntity;
import com.makkras.shop.exception.InteractionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao <T extends CustomEntity>{
    List<T> findAll() throws InteractionException;
    boolean create(T t);
    default void closeStatement(Statement statement) throws InteractionException{
        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }
    }
    default void closeConnection(Connection connection) throws InteractionException{
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }
    }
}
