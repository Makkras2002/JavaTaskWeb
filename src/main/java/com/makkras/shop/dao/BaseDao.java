package com.makkras.shop.dao;

import com.makkras.shop.entity.CustomEntity;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao <T extends CustomEntity>{
    List<T> findAll() throws InteractionException;
    Long create(T t) throws InteractionException;
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
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException exception) {
                throw new InteractionException(exception.getMessage());
            }
        }
    }
}
