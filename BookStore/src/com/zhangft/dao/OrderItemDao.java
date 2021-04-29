package com.zhangft.dao;

import com.zhangft.bean.OrderItem;

import java.util.List;

/**
 * 订单项的dao类
 */
public interface OrderItemDao {
    /**
     * 获取某个订单的所有订单项
     * @param OrderId
     * @return
     */
    public List<OrderItem> getOrderItem(String OrderId);

    /**
     * 保存某个订单项
     * @param orderItem
     */
    public int saveOrderItem(OrderItem orderItem);
}
