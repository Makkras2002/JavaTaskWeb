package com.makkras.shop.dao;

import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.InteractionException;

import java.sql.Date;
import java.util.List;


/**
 * The interface Order dao.
 */
public interface OrderDao extends BaseDao<CompleteOrder>{
    /**
     * Find all finished orders and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<CompleteOrder> findAllFinishedOrders() throws InteractionException;

    /**
     * Find all completed orders and sort by user login and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<CompleteOrder> findAllCompletedOrdersAndSortByUserLogin() throws InteractionException;

    /**
     * Find all completed orders and sort by date and collect them in the list.
     *
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<CompleteOrder> findAllCompletedOrdersAndSortByDate() throws InteractionException;

    /**
     * Find all orders in date period and collect them in the list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<CompleteOrder> findAllOrdersInDatePeriod(Date startDate,Date endDate) throws InteractionException;

    /**
     * Find all orders by user and collect them in the list.
     *
     * @param user the user
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<CompleteOrder> findAllOrdersByUser(User user) throws InteractionException;

    /**
     * Find orders by id and collect them in the list.
     *
     * @param id the id
     * @return the list
     * @throws InteractionException the interaction exception
     */
    List<CompleteOrder> findOrdersById(Long id) throws InteractionException;

    /**
     * Update complete order date and return boolean true if operation was successful.
     *
     * @param newDate         the new date
     * @param completeOrderId the complete order id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateCompleteOrderDate(Date newDate,Long completeOrderId) throws InteractionException;

    /**
     * Update complete order status and return boolean true if operation was successful.
     *
     * @param newStatus       the new status
     * @param completeOrderId the complete order id
     * @return the boolean success result
     * @throws InteractionException the interaction exception
     */
    boolean updateCompleteOrderStatus(Boolean newStatus,Long completeOrderId) throws InteractionException;
}
