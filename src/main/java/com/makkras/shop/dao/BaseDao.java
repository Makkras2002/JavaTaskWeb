package com.makkras.shop.dao;

import com.makkras.shop.entity.CustomEntity;
import com.makkras.shop.exception.InteractionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao <T extends CustomEntity>{
    /**
     * Find all data from database and save in the list.
     *
     * @return the list of data
     * @throws InteractionException the interaction exception
     */
    List<T> findAll() throws InteractionException;

    /**
     * Create data in database and return it's id.
     *
     * @param t the object to put in database
     * @return the long id of created object
     * @throws InteractionException the interaction exception
     */
    Long create(T t) throws InteractionException;

    /**
     * Close statement.
     *
     * @param statement the statement
     * @throws InteractionException the interaction exception
     */
    default void closeStatement(Statement statement) throws InteractionException{
        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
        }
    }

    /**
     * Close connection.
     *
     * @param connection the connection
     * @throws InteractionException the interaction exception
     */
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
