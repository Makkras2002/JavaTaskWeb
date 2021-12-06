package com.makkras.shop.pool;

import com.makkras.shop.exception.InteractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class CustomConnectionPool {

    private static Logger logger = LogManager.getLogger();
    private BlockingDeque<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private final static int DEFAULT_POOL_SIZE = 40;
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/Java_Pam_Web_Project";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "19091970Ig";

    private static class LoadCustomConnectionPool{
        static final CustomConnectionPool INSTANCE = new CustomConnectionPool();
    }
    private CustomConnectionPool(){
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            freeConnections = new LinkedBlockingDeque<>();
            givenAwayConnections = new ArrayDeque<>();
            int counter =0;
            while (counter < DEFAULT_POOL_SIZE){
                freeConnections.add(new ProxyConnection(DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)));
                counter++;
            }
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
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
    public void releaseConnection(Connection connection) throws InteractionException {
        if(!connection.getClass().equals(ProxyConnection.class)){
            throw new InteractionException("Unauthorized connection appeared in pool.");
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
