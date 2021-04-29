package com.zhangft.test;

import com.zhangft.bean.Book;
import com.zhangft.bean.Cart;
import com.zhangft.bean.OrderItem;
import com.zhangft.bean.User;
import com.zhangft.dao.daoImpl.OrderItemDaoImpl;
import com.zhangft.service.BookService;
import com.zhangft.service.OrderService;
import org.junit.Test;

public class OrderTest {
    @Test
    public void test1(){
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
        OrderItem orderItem = new OrderItem();
        orderItem.setBookName("张三");
        orderItem.setCount(20);
        orderItem.setPrice(20);
        orderItem.setTotalPrice(400);
        orderItem.setOrderId("12312341234");
        int x = orderItemDao.saveOrderItem(orderItem);
        System.out.println(x);
    }

    BookService bookService = new BookService();
    OrderService orderService = new OrderService();


    @Test
    public void test2(){
        Book book = new Book();
        book.setId(7);
        Book one = bookService.getBook(book);
        Cart cart = new Cart();
        /*cart.a*/
        cart.addBook2Cart(one);
        cart.addBook2Cart(one);
        System.out.println(cart);

        String orderid = orderService.checkout(cart, new User(1, "", "", ""));
        System.out.println(orderid);

    }
}
