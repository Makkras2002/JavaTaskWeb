package com.makkras.shop.dao.impl;

import com.makkras.shop.dao.OrderDao;
import com.makkras.shop.entity.*;
import com.makkras.shop.exception.InteractionException;
import com.makkras.shop.pool.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_COMPONENT_ORDERS_FOR_COMPLETE_ORDER_ID = """
            SELECT products.product_name, products.is_in_stock, product_categories.category, component_orders.ordered_product_amount, component_orders.ordered_product_full_price
            FROM component_orders JOIN products ON component_orders.product_id = products.product_id JOIN product_categories ON products.product_category_id = product_categories.category_id WHERE component_orders.component_order_id = ?""";
    private static final String SQL_SELECT_ALL_COMPLETE_ORDERS ="""
            SELECT complete_orders.complete_order_id, users.login, users.email, users.is_active,
            complete_orders.complete_order_date, complete_orders.is_completed
            FROM complete_orders JOIN users
            ON complete_orders.user_id = users.user_id""";
    private static final String SQL_SELECT_ALL_FINISHED_ORDERS ="""
            SELECT complete_orders.complete_order_id, users.login, users.email, users.is_active,
            complete_orders.complete_order_date, complete_orders.is_completed
            FROM complete_orders JOIN users
            ON complete_orders.user_id = users.user_id WHERE complete_orders.is_completed = true""";
    private static final String SQL_SELECT_ALL_COMPLETE_ORDERS_AND_SORT_BY_USER_LOGIN ="""
            SELECT complete_orders.complete_order_id, users.login, users.email, users.is_active,
            complete_orders.complete_order_date, complete_orders.is_completed
            FROM complete_orders JOIN users
            ON complete_orders.user_id = users.user_id ORDER BY users.login ASC""";
    private static final String SQL_SELECT_ALL_COMPLETE_ORDERS_AND_SORT_BY_DATE ="""
            SELECT complete_orders.complete_order_id, users.login, users.email, users.is_active,
            complete_orders.complete_order_date, complete_orders.is_completed
            FROM complete_orders JOIN users
            ON complete_orders.user_id = users.user_id ORDER BY complete_orders.complete_order_date ASC""";
    private static final String SQL_SELECT_ALL_COMPLETE_ORDERS_IN_DATE_PERIOD ="""
            SELECT complete_orders.complete_order_id, users.login, users.email, users.is_active,
            complete_orders.complete_order_date, complete_orders.is_completed
            FROM complete_orders JOIN users
            ON complete_orders.user_id = users.user_id WHERE complete_orders.complete_order_date > ? AND complete_orders.complete_order_date < ? ORDER BY complete_orders.complete_order_date ASC""";
    private static final String SQL_SELECT_ALL_COMPLETE_ORDERS_BY_USER ="""
            SELECT complete_orders.complete_order_id, users.login, users.email, users.is_active,
            complete_orders.complete_order_date, complete_orders.is_completed
            FROM complete_orders JOIN users
            ON complete_orders.user_id = users.user_id WHERE users.login = ? OR users.email = ?""";
    private static final String SQL_CREATE_COMPLETE_ORDER ="""
            INSERT INTO complete_orders (user_id, is_completed, complete_order_date)
            VALUES (?,?,?)""";
    private static final String SQL_CREATE_COMPONENT_ORDERS_FOR_COMPLETE_ORDER ="""
            INSERT INTO component_orders (component_order_id, product_id, ordered_product_amount, ordered_product_full_price)
            VALUES (?,?,?,?)""";
    private static final String SQL_UPDATE_COMPLETE_ORDER_DATE= """
    UPDATE complete_orders SET complete_order_date  = ? WHERE complete_order_id = ?""";
    private static final String SQL_UPDATE_COMPLETE_ORDER_STATUS= """
    UPDATE complete_orders SET is_completed  = ? WHERE complete_order_id = ?""";

    public OrderDaoImpl(){
    }
    @Override
    public List<CompleteOrder> findAll() throws InteractionException {
        List<CompleteOrder> orders = selectDataFromDbByQuery(SQL_SELECT_ALL_COMPLETE_ORDERS);
        return orders;
    }
    @Override
    public Long create(CompleteOrder completeOrder) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        Long createdItemKey = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_COMPLETE_ORDER,Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, completeOrder.getUser().getUserId());
            statement.setBoolean(2,completeOrder.isCompleted());
            statement.setDate(3,Date.valueOf(completeOrder.getCompleteOrderDate()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                createdItemKey = resultSet.getLong(1);
            }
            createComponentOrdersForCompleteOrder(completeOrder.getComponentOrders(),createdItemKey);
        } catch (SQLException exception) {
            throw  new InteractionException(exception.getMessage());

        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return createdItemKey;
    }

    @Override
    public List<CompleteOrder> findAllFinishedOrders() throws InteractionException {
        List<CompleteOrder> orders = selectDataFromDbByQuery(SQL_SELECT_ALL_FINISHED_ORDERS);
        return orders;
    }

    @Override
    public List<CompleteOrder> findAllCompletedOrdersAndSortByUserLogin() throws InteractionException {
        List<CompleteOrder> orders = selectDataFromDbByQuery(SQL_SELECT_ALL_COMPLETE_ORDERS_AND_SORT_BY_USER_LOGIN);
        return orders;
    }

    @Override
    public List<CompleteOrder> findAllCompletedOrdersAndSortByDate() throws InteractionException {
        List<CompleteOrder> orders = selectDataFromDbByQuery(SQL_SELECT_ALL_COMPLETE_ORDERS_AND_SORT_BY_DATE);
        return orders;
    }

    @Override
    public List<CompleteOrder> findAllOrdersInDatePeriod(Date startDate, Date endDate) throws InteractionException {
        List<CompleteOrder> orders = new ArrayList<>();
        List<ComponentOrder> componentOrders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_COMPLETE_ORDERS_IN_DATE_PERIOD);
            statement.setDate(1,startDate);
            statement.setDate(2,endDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long orderId = resultSet.getLong(1);
                String login = resultSet.getString(2);
                String email = resultSet.getString(3);
                Boolean isActive = resultSet.getBoolean(4);
                LocalDate orderDate = resultSet.getDate(5).toLocalDate();
                Boolean isCompleted = resultSet.getBoolean(6);
                componentOrders = selectComponentOrdersForCompleteOrderId(orderId);
                orders.add(new CompleteOrder(orderId,orderDate,new User(login,email,isActive),isCompleted,componentOrders));
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
        return orders;
    }

    @Override
    public List<CompleteOrder> findAllOrdersByUser(User user) throws InteractionException {
        List<CompleteOrder> orders = new ArrayList<>();
        List<ComponentOrder> componentOrders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_COMPLETE_ORDERS_BY_USER);
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getEmail());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long orderId = resultSet.getLong(1);
                String login = resultSet.getString(2);
                String email = resultSet.getString(3);
                Boolean isActive = resultSet.getBoolean(4);
                LocalDate orderDate = resultSet.getDate(5).toLocalDate();
                Boolean isCompleted = resultSet.getBoolean(6);
                componentOrders = selectComponentOrdersForCompleteOrderId(orderId);
                orders.add(new CompleteOrder(orderId,orderDate,new User(login,email,isActive),isCompleted,componentOrders));
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
        return orders;
    }

    @Override
    public boolean updateCompleteOrderDate(Date newDate, Long completeOrderId) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_COMPLETE_ORDER_DATE);
            statement.setDate(1,newDate);
            statement.setLong(2,completeOrderId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
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
    public boolean updateCompleteOrderStatus(Boolean newStatus, Long completeOrderId) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_COMPLETE_ORDER_STATUS);
            statement.setBoolean(1,newStatus);
            statement.setLong(2,completeOrderId);
            int updRes = statement.executeUpdate();
            if(updRes ==0){
                return false;
            }
        } catch (SQLException exception) {
            throw new InteractionException(exception.getMessage());
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
    private List<CompleteOrder> selectDataFromDbByQuery(String query) throws InteractionException {
        List<CompleteOrder> orders = new ArrayList<>();
        List<ComponentOrder> componentOrders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long orderId = resultSet.getLong(1);
                String login = resultSet.getString(2);
                String email = resultSet.getString(3);
                Boolean isActive = resultSet.getBoolean(4);
                LocalDate orderDate = resultSet.getDate(5).toLocalDate();
                Boolean isCompleted = resultSet.getBoolean(6);
                componentOrders = selectComponentOrdersForCompleteOrderId(orderId);
                orders.add(new CompleteOrder(orderId,orderDate,new User(login,email,isActive),isCompleted,componentOrders));
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
        return orders;
    }
    private List<ComponentOrder> selectComponentOrdersForCompleteOrderId(Long id) throws InteractionException {
        List<ComponentOrder> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_COMPONENT_ORDERS_FOR_COMPLETE_ORDER_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String productName = resultSet.getString(1);
                Boolean isInStock = resultSet.getBoolean(2);
                ProductCategory category = new ProductCategory(resultSet.getString(3));
                Long orderedProductAmount = resultSet.getLong(4);
                BigDecimal productFullPrice = resultSet.getBigDecimal(5);
                orders.add(new ComponentOrder(new Product(productName,isInStock,category),orderedProductAmount,productFullPrice));
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
        return orders;
    }
    private void createComponentOrdersForCompleteOrder(List<ComponentOrder> componentOrders, Long completeOrderId) throws InteractionException {
        Connection connection = null;
        PreparedStatement statement = null;
        Long createdItemKey = null;
        try {
            connection = CustomConnectionPool.getInstance().getConnection();
            for (ComponentOrder componentOrder :componentOrders){
                statement = connection.prepareStatement(SQL_CREATE_COMPONENT_ORDERS_FOR_COMPLETE_ORDER);
                statement.setLong(1, completeOrderId);
                statement.setLong(2,componentOrder.getProduct().getProductId());
                statement.setLong(3,componentOrder.getOrderedProductAmount());
                statement.setBigDecimal(4,componentOrder.getOrderedProductFullPrice());
                statement.executeUpdate();
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
    }
}
