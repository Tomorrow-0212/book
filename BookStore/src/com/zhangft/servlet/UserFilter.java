package com.zhangft.servlet;

import com.zhangft.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        // 判断用户是否登录
        if(user == null){
            // 用户未登录，跳转到登录页面进行登录
            request.setAttribute("message","此操作需要提前登录");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            // 用户登录，执行请求
            chain.doFilter(request, response);
        }
    }
}
