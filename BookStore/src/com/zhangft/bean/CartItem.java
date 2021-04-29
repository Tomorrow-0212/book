package com.zhangft.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    // 每个购物项的图书信息
    private Book book;
    // 每个购物项添加几本
    private int totalCount;
    // 每个购物项商品的总金额
    private double totalPrice;

    public CartItem() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * 获取单个购物项中商品的数量
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 计算单个购物项的总金额
     * @return
     */
    public double getTotalPrice() {
        // 将价格与数量转换为BigDecimal类型
        BigDecimal price = new BigDecimal(getBook().getPrice()+"");
        BigDecimal count = new BigDecimal(getTotalCount()+"");
        BigDecimal totalPrice = price.multiply(count);
        return totalPrice.doubleValue();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
