package com.zhangft.test;

import com.zhangft.bean.Book;
import com.zhangft.bean.Page;
import com.zhangft.dao.daoImpl.BookDaoImpl;
import com.zhangft.service.BookService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoTest {
    @Test
    public void bookDaoTest1(){
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> bookList = bookDaoImpl.getAllBooks();
    }

    @Test
    public void bookDaoTest2(){
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book book = new Book();
        book.setId(1);
        Book bookBean = bookDaoImpl.getBook(book);
        System.out.println(bookBean);
    }

    @Test
    public void bookDaoTest4(){
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book book = new Book();
        book.setId(22);
        book.setName("活着");
        book.setAuthor("余华");
        book.setPrice(22.11);
        book.setSales(16);
        book.setStock(55);
        boolean flag = bookDaoImpl.updateBook(book);
        System.out.println(flag);
    }
    @Test
    public void bookDaoTest5(){
        BookDaoImpl bookDao = new BookDaoImpl();
        System.out.println(bookDao.getPageBooks(1,5));
    }
    @Test
    public void bookDaoTest6(){
        BookService bookService = new BookService();
        bookService.getPageBooks("1","4");
        System.out.println(bookService.getPageBooks("1","4"));
    }
    @Test
    public void bookDaoTest7(){
        BookService bookService = new BookService();
        Page<Book> bookPage = bookService.getPageBooksByPrice("1","4","10","30");
        System.out.println(bookPage.getPageList());
    }
}
