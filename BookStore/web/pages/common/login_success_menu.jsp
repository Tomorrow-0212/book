<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021-3-10 0010
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
<span>欢迎<span class="um_span">${user.username}</span>光临尚硅谷书城</span>
<a href="pages/cart/cart.jsp">购物车</a>
<a href="client/clientOrderServlet?method=myOrder">我的订单</a>
<a href="userServlet?method=logout">注销</a>&nbsp;&nbsp;
<a href="index.jsp">返回</a>
</div>