package com.zhangft.service;

import com.zhangft.bean.Book;
import com.zhangft.bean.Page;
import com.zhangft.dao.BookDao;
import com.zhangft.dao.daoImpl.BookDaoImpl;

import java.util.List;

public class BookService {

    BookDao bookDao = new BookDaoImpl();

    /**
     * 查询数据库中所有的图书数据
     * @return
     */
    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    /**
     * 按条件查询数据库中的图书数据，主要用于分页显示
     * @param pageNo 在页面要访问的页码
     * @param pageSize 每页显示数据的个数
     * @return
     */
    public Page<Book> getPageBooks(String pageNo,String pageSize){
        // 定义分页对象
        Page<Book> page = new Page<Book>();

        // 查询出数据库中图书的总数量,并封装到页对象中
        int allCount = bookDao.allCount();
        page.setAllCount(allCount);

        int pageSizeData = page.getpageSize();
        int pageNoData = 1;
        try{
            // 每页显示多少个
            pageSizeData = Integer.parseInt(pageSize);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        try{
            // 第几页
            pageNoData = Integer.parseInt(pageNo);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        page.setpageSize(pageSizeData);
        page.setPageNo(pageNoData);

        List<Book> pageBooks = bookDao.getPageBooks(page.getIndex(),page.getpageSize());
        // 将每页要显示的图书信息封装到页对象中
        page.setPageList(pageBooks);

        return page;
    }

    public Page<Book> getPageBooksByPrice(String pageNo,String pageSize,String minPrice,String maxPrice){
        // 定义分页对象
        Page<Book> page = new Page<Book>();

        int pageNoData = 1;
        int pageSizeData = page.getpageSize();
        double minPriceData = 0.0;
        double maxPriceData = Double.MAX_VALUE;

        try{
            // 每页显示多少个
            pageSizeData = Integer.parseInt(pageSize);
            pageNoData = Integer.parseInt(pageNo);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        try{
            // 第几页
            pageNoData = Integer.parseInt(pageNo);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        try{
            if (minPrice != null){
                minPriceData = Double.parseDouble(minPrice);
            }
            if(maxPrice != null){
                maxPriceData = Double.parseDouble(maxPrice);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        // 根据价格区间查询出数据库中图书的数量,并封装到页对象中
        int allCount = bookDao.allCountByPrice(minPriceData,maxPriceData);
        page.setAllCount(allCount);

        page.setpageSize(pageSizeData);
        page.setPageNo(pageNoData);

        List<Book> pageBooks = bookDao.getPageBooksByPrice(page.getIndex(),pageSizeData,minPriceData,maxPriceData);

        // 将每页要显示的图书信息封装到页对象中
        page.setPageList(pageBooks);
        return page;
    }

    /**
     * 查询数据库中的一本图书
     * @param book
     * @return
     */
    public Book getBook(Book book){
        return bookDao.getBook(book);
    }

    /**
     * 删除数据库中的一本图书
     * @param book
     * @return
     */
    public boolean deleteBook(Book book){
        return bookDao.deleteBook(book);
    }

    /**
     * 修改数据库中的一本图书
     * @param book
     * @return
     */
    public boolean updateBook(Book book){
        return bookDao.updateBook(book);
    }
    /**
     * 向数据库中添加一本图书
     * @param book
     * @return
     */
    public boolean addBook(Book book){
        return bookDao.addBook(book);
    }
}
