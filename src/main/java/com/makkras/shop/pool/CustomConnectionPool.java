package com.makkras.shop.pool;

import com.makkras.shop.exception.PoolCustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The type Custom connection pool.
 */
public class CustomConnectionPool {

    private static final int DEFAULT_POOL_SIZE = 15;
    private static Logger logger = LogManager.getLogger();
    private BlockingDeque<ProxyConnection> freeConnections;
    private BlockingDeque<ProxyConnection> givenAwayConnections;

    private static class LoadCustomConnectionPool{
        /**
         * The Instance.
         */
        static final CustomConnectionPool INSTANCE = new CustomConnectionPool();
    }
    private CustomConnectionPool(){
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(DataBaseProperties.DATABASE_PROPERTIES_FILE_NAME);
            String dbUrl = bundle.getString(DataBaseProperties.DATABASE_URL_KEY);
            String dbUser = bundle.getString(DataBaseProperties.DATABASE_USERNAME_KEY);
            String dbPassword = bundle.getString(DataBaseProperties.DATABASE_PASSWORD_KEY);
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

        } catch (SQLException | MissingResourceException exception) {
            logger.fatal(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * Get instance of custom connection pool.
     *
     * @return the custom connection pool
     */
    public static CustomConnectionPool getInstance(){
        return LoadCustomConnectionPool.INSTANCE;
    }

    /**
     * Get connection and return connection.
     *
     * @return the connection
     */
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

    /**
     * Release connection.
     *
     * @param connection the connection
     * @throws PoolCustomException the pool custom exception
     */
    public void releaseConnection(Connection connection) throws PoolCustomException {
        if(connection.getClass() != ProxyConnection.class){
            throw new PoolCustomException("Unauthorized connection appeared in pool.");
        }
        givenAwayConnections.remove(connection);
        freeConnections.offer((ProxyConnection) connection);
    }

    /**
     * Destroy pool.
     */
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
