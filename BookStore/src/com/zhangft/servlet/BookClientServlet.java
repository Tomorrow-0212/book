package com.zhangft.servlet;

import com.zhangft.bean.Book;
import com.zhangft.bean.Page;
import com.zhangft.service.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class BookClientServlet extends BaseServlet {
    /**
     * 按照价格查询图书并按照要求分页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");

        BookService bookService = new BookService();
        Page<Book> pageList =  bookService.getPageBooksByPrice(pageNo,pageSize,minPrice,maxPrice);
        String url = "client/bookClientServlet?method=getPage&minPrice=";
        if(minPrice != null){
            url += minPrice + "&maxPrice=";
        }else{
            url += " &maxPrice=";
        }
        if(maxPrice != null){
            url += maxPrice;
        }
        // 将跳转的URL封装
        pageList.setUrl(url);
        request.setAttribute("pageList",pageList);
        request.getRequestDispatcher("/pages/book/book_client.jsp").forward(request,response);
    }
}
