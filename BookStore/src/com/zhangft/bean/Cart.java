package com.zhangft.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    // 保存了所有的购物项
    private Map<Integer,CartItem> cartItemMap = new LinkedHashMap<>();
    // 购物车中商品的总数量
    private int totalCount;
    // 购物车中所有商品的总金额
    private double totalPrice;

    /**
     * 返回购物车中所有的购物项
     * 主要用于在显示页面遍历展示
     * @return
     */
    public List<CartItem> getAllCartItem(){
        Collection<CartItem> value = cartItemMap.values();
        return new ArrayList<>(value);
    }

    /**
     * 获取购物车中商品的总数量
     * @return
     */
    public int getTotalCount() {
        int count = 0;
        List<CartItem> list = getAllCartItem();
        for(CartItem cartItem : list){
            count += cartItem.getTotalCount();
        }
        return count;
    }

    /**
     * 获取购物车中商品的总价格
     * @return
     */
    public double getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0.0+"");
        List<CartItem> list = getAllCartItem();
        for(CartItem cartItem : list){
            BigDecimal price = new BigDecimal(cartItem.getTotalPrice()+"");
            totalPrice = totalPrice.add(price);
        }
        return totalPrice.doubleValue();
    }

    /**
     * 向购物车中添加图书
     * @param book
     */
    public void addBook2Cart(Book book){
        // 判断购物车中是否有此ID的购物项
        CartItem item = cartItemMap.get(book.getId());
        if(item == null){
            // 购物车中没有此购物项
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setTotalCount(1);
            cartItemMap.put(book.getId(),cartItem);
        }else{
            item.setBook(book);
            item.setTotalCount(item.getTotalCount() + 1);
            cartItemMap.put(book.getId(),item);
        }
    }

    /**
     * 从购物车中删除某一购物项
     * @param bookId
     */
    public void deleteItem(String bookId){
        if(bookId != null){
            int id = Integer.parseInt(bookId);
            cartItemMap.remove(id);
        }
    }

    /**
     * 修改购物车中购物项的商品数量
     * @param bookId
     * @param cartItemCount
     */
    public void updateCount(String bookId,String cartItemCount){
        int id = 0;
        int count = 1;
        if(bookId != null){
            id = Integer.parseInt(bookId);
        }
        if (cartItemCount != null){
            count = Integer.parseInt(cartItemCount);
            count = count >= 1 ? count:1;
        }
        CartItem item = cartItemMap.get(id);
        if(item != null){
            item.setTotalCount(count);
        }
    }

    /**
     * 清空购物车
     */
    public void clear(){
        cartItemMap.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItemMap=" + cartItemMap +
                ", totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
