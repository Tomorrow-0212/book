package com.zhangft.dao;

import com.zhangft.bean.Order;
import com.zhangft.bean.OrderItem;

import java.util.List;

/**
 * 订单管理的dao类
 */
public interface OrderDao {
    /**
     * 保存一个订单的方法
     * @param order
     * @return
     */
    public int saveOrder(Order order);

    /**
     * 修改订单状态的方法
     * @param order
     * @return
     */
    public int updateStatus(Order order);

    /**
     * 获取所有订单的方法（管理员后台使用）
     * @return
     */
    public List<Order> getAllOrder();

    /**
     * 通过userID获取某个用户的订单
     * @return
     */
    public List<Order> getOrderByUserId(Integer userId);
}
