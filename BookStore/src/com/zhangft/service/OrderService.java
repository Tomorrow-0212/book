package com.zhangft.service;

import com.zhangft.bean.*;
import com.zhangft.dao.OrderDao;
import com.zhangft.dao.daoImpl.OrderDaoImpl;

import java.util.Date;
import java.util.List;

/**
 * 订单的service类
 */
public class OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemService orderItemService = new OrderItemService();
    BookService bookService = new BookService();

    /**
     * 提交订单的方法
     * @param cart
     * @param user
     * @return
     */
    public String checkout(Cart cart, User user){
        // 1.生成订单号
        long millis = System.currentTimeMillis();
        String orderId = millis + "" + user.getId();

        // 2.保存订单
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCreateDate(new Date());
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(0);
        order.setUserId(user.getId());
        orderDao.saveOrder(order);

        // 3.封装订单项对象,并保存
        List<CartItem> allCartItem = cart.getAllCartItem();
        for(CartItem cartItem : allCartItem){
            OrderItem orderItem = new OrderItem(cartItem.getBook().getName(),cartItem.getTotalCount(),cartItem.getBook().getPrice(),
                    cartItem.getTotalPrice(),orderId);
            orderItemService.saveItem(orderItem);
        }

        // 4.修改数据库中库存与销量
        for(CartItem cartItem : allCartItem){
            int count = cartItem.getTotalCount();
            Book book = cartItem.getBook();
            // 从数据库中获取图书的信息，保证多个用户同时提交订单时，数据库能够全部修改
            Book oneBook = bookService.getBook(book);
            oneBook.setStock(oneBook.getStock() - count);
            oneBook.setSales(oneBook.getSales() + count);
            bookService.updateBook(oneBook);
        }
        return orderId;
    }

    /**
     * 修改订单状态的方法
     * @param orderId
     * @param status
     */
    public void update(String orderId,String status){
        int statusData = 0;
        if(status != null){
            statusData = Integer.parseInt(status);
        }
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(statusData);
        orderDao.updateStatus(order);
    }

    /**
     * 获取所有订单的方法，主要是管理员在后台使用
     * @return
     */
    public List<Order> getAllOrder(){
        List<Order> result = orderDao.getAllOrder();
        return result;
    }

    /**
     * 通过userID获取到某个用户的所有订单
     * @param userId
     * @return
     */
    public List<Order> getMyOrder(Integer userId){
        List<Order> result = orderDao.getOrderByUserId(userId);
        return result;
    }
}
