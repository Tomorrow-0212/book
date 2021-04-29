<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<!--base标签、jQuery、CSS样式静态包含-->
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<!--图书管理静态包含-->
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>订单状态</td>
			</tr>
			<c:forEach var="allOrder" items="${requestScope.allOrderList}">
				<tr>
					<td>${allOrder.orderId}</td>
					<td>${allOrder.createDate}</td>
					<td>${allOrder.totalPrice}</td>
					<td><a href="#">查看详情</a></td>
					<td>
						<c:choose>
							<c:when test="${allOrder.status == 0}"><a href="manager/managerOrderServlet?method=send&orderId=${allOrder.orderId}">点击发货</a></c:when>
							<c:when test="${allOrder.status == 1}">已发货</c:when>
							<c:when test="${allOrder.status == 2}">确认收货</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!--页脚静态包含-->
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>