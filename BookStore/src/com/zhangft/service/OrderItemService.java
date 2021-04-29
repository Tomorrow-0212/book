package com.zhangft.service;

import com.zhangft.bean.OrderItem;
import com.zhangft.dao.OrderItemDao;
import com.zhangft.dao.daoImpl.OrderItemDaoImpl;

import java.util.List;

/**
 * 订单项的service类
 */
public class OrderItemService {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    /**
     * 根据订单号获取订单项集合的方法
     * @param orderId
     * @return
     */
    public List<OrderItem> getOrderItems(String orderId){
        List<OrderItem> orderItemList = orderItemDao.getOrderItem(orderId);
        return orderItemList;
    }

    /**
     * 保存订单项的方法
     * @param orderItem
     */
    public void saveItem(OrderItem orderItem){
        orderItemDao.saveOrderItem(orderItem);
    }
}
