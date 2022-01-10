package com.makkras.shop.service;

import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface CustomOrderService {
    void makeOrderAndSendNotificationMessageOnUserEmail(List<ComponentOrder> componentOrders, String userLogin, String currentLocale) throws ServiceException;
    List<CompleteOrder> findAllOrdersFromDb() throws ServiceException;
    List<CompleteOrder> findAllOrdersFromDbAndSortByDate() throws ServiceException;
    List<CompleteOrder> findAllOrdersFromDbAndSortByLogin() throws ServiceException;
    List<CompleteOrder> findOrdersFromDbByParams(String login, LocalDate startDate, LocalDate endDate, Boolean status) throws ServiceException;
    boolean completeOrder(Long orderId) throws ServiceException;
}
