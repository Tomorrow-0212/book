package com.zhangft.bean;

import java.io.Serializable;

/**
 * 每个订单中每个订单项的详细信息
 */
public class OrderItem implements Serializable {
    private int id;
    private String bookName;
    private int count;
    private double price;
    private double totalPrice;
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(String bookName, int count, double price, double totalPrice, String orderId) {
        this.bookName = bookName;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId=" + orderId +
                '}';
    }
}
