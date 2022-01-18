package com.makkras.shop.service.impl;

import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.CustomOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrderServiceTest {
    private static Logger logger = LogManager.getLogger();

    private String login;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean status;

    @Mock
    private CustomOrderService orderServiceMock;

    @BeforeClass
    public void setUp() {
        orderServiceMock = Mockito.mock(OrderService.class);
        try {
            Mockito.when(orderServiceMock.findAllOrdersFromDb()).thenThrow(ServiceException.class);
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Test(expectedExceptions = {ServiceException.class})
    public void findOrdersFromDbByParamsTest() throws ServiceException {
        List<CompleteOrder> allOrders = orderServiceMock.findAllOrdersFromDb();
        List<CompleteOrder> requiredOrders = new ArrayList<>();
        int counter = 0;
        while (counter < allOrders.size()){
            if(((allOrders.get(counter).getUser().getLogin().contains(login) && login.length()>3) || login.isBlank()) &&
                    allOrders.get(counter).getCompleteOrderDate().isAfter(startDate) &&
                    allOrders.get(counter).getCompleteOrderDate().isBefore(endDate)) {
                if(status == null) {
                    requiredOrders.add(allOrders.get(counter));
                } else if(allOrders.get(counter).isCompleted() == status){
                    requiredOrders.add(allOrders.get(counter));
                }
            }
            counter++;
        }
    }

    @AfterClass
    public void after() {
        try {
            Mockito.verify(orderServiceMock,Mockito.atMost(1)).findAllOrdersFromDb();
        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        }
    }
}