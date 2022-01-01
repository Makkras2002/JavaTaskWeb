package com.makkras.shop.service;

import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.exception.ServiceException;

import java.util.List;

public interface CustomOrderService {
    void makeOrderAndSendNotificationMessageOnUserEmail(List<ComponentOrder> componentOrders, String userLogin, String currentLocale) throws ServiceException;
    List<CompleteOrder> findAllOrdersFromDb() throws ServiceException;
}
