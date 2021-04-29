package com.zhangft.dao.daoImpl;

import com.zhangft.bean.Book;
import com.zhangft.dao.BaseDao;
import com.zhangft.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    /**
     * 查询数据库中所有的图书数据
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book";
        List<Book> bookList = this.getBeanList(sql);
        return bookList;
    }

    /**
     * 按条件查询数据库中的图书数据，主要用于分页显示
     * @param index 数据库开始索引的位置
     * @param size 每次查询的数量
     * @return
     */
    @Override
    public List<Book> getPageBooks(int index, int size) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book limit ?,?";
        Object[] params = {index,size};
        List<Book> bookPageList = this.getBeanList(sql,params);
        return bookPageList;
    }

    /**
     * 根据价格条件查询图书
     * @param index
     * @param size
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public List<Book> getPageBooksByPrice(int index, int size, double minPrice, double maxPrice) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where price between ? and ? limit ?,?";
        Object[] params = {minPrice,maxPrice,index,size};
        List<Book> bookPageList = this.getBeanList(sql,params);
        return bookPageList;
    }

    /**
     * 查询数据库中的一本图书
     * @param book
     * @return
     */
    @Override
    public Book getBook(Book book) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where id=?";
        Book bookBean = this.getBean(sql,book.getId());
        return bookBean;
    }

    /**
     * 删除数据库中的一本图书
     * @param book
     * @return
     */
    @Override
    public boolean deleteBook(Book book) {
        String sql = "delete from t_book where id=?";
        int result = this.update(sql,book.getId());
        return result > 0;
    }

    /**
     * 修改数据库中的一本图书
     * @param book
     * @return
     */
    @Override
    public boolean updateBook(Book book) {
        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        Object[] params = {book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId()};
        int result = this.update(sql,params);
        return result > 0;
    }

    /**
     * 向数据库中添加一本图书
     * @param book
     * @return
     */
    @Override
    public boolean addBook(Book book) {
        String sql = "insert into t_book (name,author,price,sales,stock,img_path) values (?,?,?,?,?,?)";
        Object[] params = {book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath()};
        int result = this.update(sql,params);
        return result > 0;
    }

    /**
     * 查询出数据库中图书的总数量
     * @return
     */
    @Override
    public int allCount() {
        String sql = "select count(*) from t_book";
        Object result = this.getSingleValue(sql);
        int count = Integer.parseInt(result.toString());
        return count;
    }

    /**
     * 根据价格区间查询图书的数量
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public int allCountByPrice(double minPrice, double maxPrice) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Object[] params = {minPrice,maxPrice};
        Object result = this.getSingleValue(sql,params);
        int count = Integer.parseInt(result.toString());
        return count;
    }
}
