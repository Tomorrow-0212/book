<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!--base标签，永远固定相对路径的跳转结果-->
<%
    String basePath = request.getScheme()
        + "://"
        + request.getServerName()
        + ":"
        + request.getServerPort()
        + request.getContextPath();
%>
<base href= <%=basePath%>/>
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<!--引入jquery文件  -->
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>