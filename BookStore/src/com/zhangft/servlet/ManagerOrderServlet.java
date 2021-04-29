package com.zhangft.servlet;

import com.zhangft.bean.Order;
import com.zhangft.bean.User;
import com.zhangft.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ManagerOrderServlet extends BaseServlet {

    OrderService orderService = new OrderService();

    /**
     * 跳转到管理员管理订单页面的servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void managerOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Order> allOrderList = orderService.getAllOrder();
        request.setAttribute("allOrderList",allOrderList);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }


    /**
     * 管理员发货的servlet方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String status = "1";
        orderService.update(orderId,status);
        request.getRequestDispatcher("/manager/managerOrderServlet?method=managerOrder").forward(request,response);
    }

    protected void receiving(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String status = "2";
        orderService.update(orderId,status);
        request.getRequestDispatcher("/client/clientOrderServlet?method=myOrder").forward(request,response);
    }
}
