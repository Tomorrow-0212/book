package com.zhangft.servlet;

import com.zhangft.bean.Cart;
import com.zhangft.bean.Order;
import com.zhangft.bean.User;
import com.zhangft.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ClientOrderServlet extends BaseServlet {
    OrderService orderService = new OrderService();

    /**
     * 购物车的结算功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 获取session中的cart对象
        Cart cart = (Cart) session.getAttribute("cart");
        String orderId = orderService.checkout(cart, user);
        session.setAttribute("orderId",orderId);
        cart.clear();
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 通过用户的ID获取该用户的购物订单列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Order> orderList = orderService.getMyOrder(user.getId());
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
    }
}
