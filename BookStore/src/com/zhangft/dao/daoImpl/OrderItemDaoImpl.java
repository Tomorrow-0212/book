package com.zhangft.dao.daoImpl;

import com.zhangft.bean.OrderItem;
import com.zhangft.dao.BaseDao;
import com.zhangft.dao.OrderItemDao;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    /**
     * 获取某个订单的所有订单项
     * @param OrderId
     * @return
     */
    @Override
    public List<OrderItem> getOrderItem(String OrderId) {
        String sql = "select id,book_name bookName,count,price,total_price totalPrice,order_id OrderId from t_order_item";
        List<OrderItem> result = this.getBeanList(sql);
        return result;
    }

    /**
     * 保存某个订单项
     * @param orderItem
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item (book_name,count,price,total_price,order_id) values (?,?,?,?,?)";
        Object[] params = {orderItem.getBookName(),orderItem.getCount(),orderItem.getPrice(), orderItem.getTotalPrice(),orderItem.getOrderId()};
        int result = this.update(sql,params);
        return result;
    }
}
