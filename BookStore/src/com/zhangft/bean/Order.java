package com.zhangft.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单的详细信息
 */
public class Order implements Serializable {
    private String orderId;
    private Date createDate;
    private double totalPrice;
    private int status;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createDate, double totalPrice, int status, Integer userId) {
        this.orderId = orderId;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
