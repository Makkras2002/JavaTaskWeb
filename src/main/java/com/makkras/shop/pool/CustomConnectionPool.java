package com.makkras.shop.pool;

import com.makkras.shop.exception.PoolCustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class CustomConnectionPool {

    private static final int DEFAULT_POOL_SIZE = 40;
    private static Logger logger = LogManager.getLogger();
    private BlockingDeque<ProxyConnection> freeConnections;
    private BlockingDeque<ProxyConnection> givenAwayConnections;

    private static class LoadCustomConnectionPool{
        static final CustomConnectionPool INSTANCE = new CustomConnectionPool();
    }
    private CustomConnectionPool(){
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("datasrc.databaseprop");
            String dbUrl = bundle.getString("jdbc.url");
            String dbUser = bundle.getString("jdbc.user");
            String dbPassword = bundle.getString("jdbc.password");
            DriverManager.registerDriver(new org.postgresql.Driver());
            freeConnections = new LinkedBlockingDeque<>();
            givenAwayConnections = new LinkedBlockingDeque<>();
            while (true){
                int counter =freeConnections.size();
                while (counter < DEFAULT_POOL_SIZE){
                    freeConnections.add(new ProxyConnection(DriverManager.getConnection(dbUrl,dbUser,dbPassword)));
                    counter++;
                }
                if(freeConnections.size() == DEFAULT_POOL_SIZE){
                    break;
                }
            }

        } catch (SQLException exception) {
            logger.fatal(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }
    public static CustomConnectionPool getInstance(){
        return LoadCustomConnectionPool.INSTANCE;
    }
    public Connection getConnection(){
        Connection connection =null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer((ProxyConnection) connection);
        } catch (InterruptedException e) {
           logger.error(e.getMessage());
        }
        return connection;
    }
    public void releaseConnection(Connection connection) throws PoolCustomException {
        if(connection.getClass() != ProxyConnection.class){
            throw new PoolCustomException("Unauthorized connection appeared in pool.");
        }
        givenAwayConnections.remove(connection);
        freeConnections.offer((ProxyConnection) connection);
    }
    public void destroyPool(){
        int counter =0;
        while(counter < DEFAULT_POOL_SIZE){
            try {
                freeConnections.take().actuallyClose();
            }catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            counter++;
        }
        deregisterDrivers();
    }
    private void deregisterDrivers(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exception) {
                logger.error(exception.getMessage());
            }
        });
    }
}
