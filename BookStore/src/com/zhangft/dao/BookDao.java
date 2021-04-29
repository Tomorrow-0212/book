package com.zhangft.dao;

import com.zhangft.bean.Book;

import java.util.List;

public interface BookDao{
    /**
     * 查询数据库中所有的图书数据
     * @return
     */
    public List<Book> getAllBooks();

    /**
     * 按条件查询数据库中的图书数据，主要用于分页显示
     * @param index 数据库开始索引的位置
     * @param size 每次查询的数量
     * @return
     */
    public List<Book> getPageBooks(int index,int size);

    /**
     * 根据价格条件查询图书
     * @param index
     * @param size
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public List<Book> getPageBooksByPrice(int index,int size,double minPrice,double maxPrice);

    /**
     * 查询数据库中的一本图书
     * @param book
     * @return
     */
    public Book getBook(Book book);

    /**
     * 删除数据库中的一本图书
     * @param book
     * @return
     */
    public boolean deleteBook(Book book);

    /**
     * 修改数据库中的一本图书
     * @param book
     * @return
     */
    public boolean updateBook(Book book);

    /**
     * 向数据库中添加一本图书
     * @param book
     * @return
     */
    public boolean addBook(Book book);

    /**
     * 查询数据库中的所有图书的数量
     * @return
     */
    public int allCount();

    /**
     * 通过价格区间查询图书的数量
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public int allCountByPrice(double minPrice,double maxPrice);
}
