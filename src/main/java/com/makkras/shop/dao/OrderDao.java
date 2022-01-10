package com.makkras.shop.dao;

import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.entity.User;
import com.makkras.shop.exception.InteractionException;

import java.sql.Date;
import java.util.List;

public interface OrderDao extends BaseDao<CompleteOrder>{
    List<CompleteOrder> findAllFinishedOrders() throws InteractionException;
    List<CompleteOrder> findAllCompletedOrdersAndSortByUserLogin() throws InteractionException;
    List<CompleteOrder> findAllCompletedOrdersAndSortByDate() throws InteractionException;
    List<CompleteOrder> findAllOrdersInDatePeriod(Date startDate,Date endDate) throws InteractionException;
    List<CompleteOrder> findAllOrdersByUser(User user) throws InteractionException;
    List<CompleteOrder> findOrdersById(Long id) throws InteractionException;
    boolean updateCompleteOrderDate(Date newDate,Long completeOrderId) throws InteractionException;
    boolean updateCompleteOrderStatus(Boolean newStatus,Long completeOrderId) throws InteractionException;
}
