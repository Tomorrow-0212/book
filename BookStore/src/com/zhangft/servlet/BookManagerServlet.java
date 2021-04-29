package com.zhangft.servlet;

import com.zhangft.bean.Book;
import com.zhangft.bean.Page;
import com.zhangft.service.BookService;
import com.zhangft.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookManagerServlet extends BaseServlet {

    BookService bookService = new BookService();

    /**
     * 获取所有图书数据，作为列表显示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("测试Git的使用");
        List<Book> booksList = bookService.getAllBooks();
        request.setAttribute("booksList",booksList);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 删除指定ID的图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = Integer.parseInt(pageNoParam);
        Book book = WebUtils.params2bean(request,new Book());
        boolean result = bookService.deleteBook(book);
        if(result){
            response.sendRedirect(request.getContextPath() + "/manager/bookManagerServlet?method=getPage&pageNo=" + pageNo);
        }
    }

    /**
     * 向数据库中添加图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = Integer.parseInt(pageNoParam);
        // 如果添加之后变页需要加1
        pageNo++;
        Book bookBean = WebUtils.params2bean(request,new Book());
        boolean result = bookService.addBook(bookBean);
        // 重定向到图书列表界面
        response.sendRedirect(request.getContextPath() + "/manager/bookManagerServlet?method=getPage&pageNo=" + pageNo);
    }

    /**
     * 用于图书数据回显的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void echoBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.params2bean(request,new Book());
        Book bookBean = bookService.getBook(book);
        request.setAttribute("bookBean",bookBean);
        request.getRequestDispatcher("/pages/manager/book_edit4update.jsp").forward(request,response);
    }

    /**
     * 修改图书的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = Integer.parseInt(pageNoParam);
        Book book = WebUtils.params2bean(request,new Book());
        boolean result = bookService.updateBook(book);
        response.sendRedirect(request.getContextPath() + "/manager/bookManagerServlet?method=getPage&pageNo=" + pageNo);
    }

    /**
     * 获取分页图书信息的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        Page<Book> pageList =  bookService.getPageBooks(pageNo,pageSize);
        // 将跳转的URL封装
        pageList.setUrl("manager/bookManagerServlet?method=getPage");
        request.setAttribute("pageList",pageList);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
