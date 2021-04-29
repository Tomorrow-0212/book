<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <!--base标签、jQuery、CSS样式静态包含-->
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <div>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span> &nbsp;&nbsp;
                <a href="userServlet?method=logout">注销</a>&nbsp;&nbsp;&nbsp;
            </c:when>
            <c:when test="${sessionScope.user == null}">
                <a href="pages/user/login.jsp">登录</a> &nbsp;
            </c:when>
        </c:choose>
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookClientServlet" method="get" >
                <input type="hidden" name="method" value="getPage">
                价格：<input id="minPrice" type="text" name="minPrice" value="${param.minPrice}"> 元 -
                      <input id="maxPrice" type="text" name="maxPrice" value="${param.maxPrice}"> 元
                        <button>查询</button>
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有
                <c:out value="${sessionScope.cart.totalCount}" default="0"></c:out>
                件商品
            </span>

            <c:if test="${!empty sessionScope.bookName}">
                <div>
                    您刚刚将<span style="color: red">${sessionScope.bookName}</span>加入到了购物车中
                </div>
            </c:if>
        </div>


        <c:forEach var="book" items="${requestScope.pageList.pageList}" >
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <form name="addCart" action="client/cartServlet" method="post">
                        <div class="book_add">
                            <input type="hidden" name="method" value="add">
                            <input type="hidden" name="id" value="${book.id}">
                            <button>加入购物车</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <%@include file="/pages/common/page.jsp"%>
</div>
<!--页脚静态包含-->
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
