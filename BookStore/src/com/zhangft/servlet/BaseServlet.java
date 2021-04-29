package com.zhangft.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * 使用反射处理前台传过来的参数，调用响应的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        try {
            // 通过反射，找到要调用的方法
            Method methodReflect = this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            // 将方法的权限放大
            methodReflect.setAccessible(true);
            // 调用这个方法（谁要调用，这个方法的参数列表）
            methodReflect.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
