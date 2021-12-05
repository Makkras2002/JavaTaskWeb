package com.makkras.shop.dao;

import com.makkras.shop.entity.CompleteOrder;

import java.util.List;

public interface OrderDao extends BaseDao<CompleteOrder>{
    List<CompleteOrder> findAllCompletedOrders();
}
