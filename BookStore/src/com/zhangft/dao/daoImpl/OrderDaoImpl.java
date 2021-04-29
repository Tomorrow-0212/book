package com.zhangft.dao.daoImpl;

import com.zhangft.bean.Order;
import com.zhangft.bean.OrderItem;
import com.zhangft.dao.BaseDao;
import com.zhangft.dao.OrderDao;

import java.util.List;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    /**
     * 保存一个订单的方法
     * @param order
     * @return
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order (order_id,create_date,total_price,status,user_id) values (?,?,?,?,?)";
        Object[] params = {order.getOrderId(),order.getCreateDate(),order.getTotalPrice(),order.getStatus(),order.getUserId()};
        int result = this.update(sql,params);
        return result;
    }

    /**
     * 修改订单状态的方法
     * @param order
     * @return
     */
    @Override
    public int updateStatus(Order order) {
        String sql = "update t_order set status = ? where order_id = ?";
        int result = this.update(sql,order.getStatus(),order.getOrderId());
        return result;
    }

    /**
     * 获取所有订单的方法（管理员后台使用）
     * @return
     */
    @Override
    public List<Order> getAllOrder() {
        String sql = "select order_id orderId,create_date createDate,total_price totalPrice,status,user_id userId from t_order";
        List<Order> result = this.getBeanList(sql);
        return result;
    }

    /**
     * 通过userID获取某个用户的订单
     * @return
     */
    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        String sql = "select order_id orderId,create_date createDate,total_price totalPrice,status,user_id userId from t_order where user_id = ?";
        List<Order> result = this.getBeanList(sql,userId);
        return result;
    }
}
