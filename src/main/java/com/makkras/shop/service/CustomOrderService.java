package com.makkras.shop.service;

import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Custom order service.
 */
public interface CustomOrderService {
    /**
     * Make order and send notification message on user email.
     *
     * @param componentOrders the component orders
     * @param userLogin       the user login
     * @param currentLocale   the current locale, chosen by current user
     * @throws ServiceException the service exception
     */
    void makeOrderAndSendNotificationMessageOnUserEmail(List<ComponentOrder> componentOrders, String userLogin, String currentLocale) throws ServiceException;

    /**
     * Find all orders from db and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<CompleteOrder> findAllOrdersFromDb() throws ServiceException;

    /**
     * Find all orders from db and sort by date, and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<CompleteOrder> findAllOrdersFromDbAndSortByDate() throws ServiceException;

    /**
     * Find all orders from db and sort by login, and collect them in the list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<CompleteOrder> findAllOrdersFromDbAndSortByLogin() throws ServiceException;

    /**
     * Find orders from db by params, and collect them in the list.
     *
     * @param login     the login
     * @param startDate the start date
     * @param endDate   the end date
     * @param status    the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<CompleteOrder> findOrdersFromDbByParams(String login, LocalDate startDate, LocalDate endDate, Boolean status) throws ServiceException;

    /**
     * Complete order and return boolean result if order was successfully completed.
     *
     * @param orderId the order id
     * @return the boolean success result
     * @throws ServiceException the service exception
     */
    boolean completeOrder(Long orderId) throws ServiceException;
}
