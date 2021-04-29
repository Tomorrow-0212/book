package com.zhangft.servlet;

import com.zhangft.bean.Book;
import com.zhangft.bean.Cart;
import com.zhangft.service.BookService;
import com.zhangft.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CartServlet extends BaseServlet{
    BookService bookService = new BookService();
    /**
     * 向购物车中添加商品
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = (Book) WebUtils.params2bean(request,new Book());
        Book realBook = bookService.getBook(book);
        HttpSession session =  request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // 判断session中是否有cart属性
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addBook2Cart(realBook);
        session.setAttribute("bookName",realBook.getName());
        // 获取请求来的url
        String referer = request.getHeader("referer");
        response.sendRedirect(referer);
    }

    /**
     * 删除一个商品项的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("id");
        HttpSession session =  request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // 判断session中是否有cart属性
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.deleteItem(bookId);
        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
    }

    /**
     * 清空购物车的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // 判断session中是否有cart属性
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.clear();
        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
    }

    /**
     * 修改商品项数量的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String totalCount = request.getParameter("totalCount");
        String bookId = request.getParameter("id");
        HttpSession session =  request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        // 判断session中是否有cart属性
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.updateCount(bookId,totalCount);
        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
    }
}
