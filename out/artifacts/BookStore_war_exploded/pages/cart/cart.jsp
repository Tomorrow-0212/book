<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<!--base标签、jQuery、CSS样式静态包含-->
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteClass").click(function () {
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】？");
			});
			$("a.clearClass").click(function () {
				return confirm("你确定要清空购物车吗？");
			});

			$("#updateCount").change(function (){
				location.href="client/cartServlet?method=update&id=" + $(this).attr("idData") + "&totalCount="+this.value;
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<th>商品名称</th>
				<th>数量</th>
				<th>单价</th>
				<th>金额</th>
				<th>操作</th>
			</tr>
			<c:forEach var="cartItem" items="${sessionScope.cart.allCartItem}">
				<tr>
					<td>${cartItem.book.name}</td>
					<td><input type="text" name="totalCount" style="width: 40px" value="${cartItem.totalCount}" id="updateCount" idData="${cartItem.book.id}"></td>
					<td>${cartItem.totalPrice}</td>
					<td><a href="client/cartServlet?method=delete&id=${cartItem.book.id}" class="deleteClass">删除</a></td>
				</tr>
			</c:forEach>
		</table>

		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">
				<c:out value="${sessionScope.cart.totalCount}" default="0"></c:out>
			</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">
				<c:out value="${sessionScope.cart.totalPrice}" default="0"></c:out>
			</span>元</span>
			<span class="cart_span"><a href="client/cartServlet?method=clear" class="clearClass">清空购物车</a></span>
			<span class="cart_span"><a href="client/clientOrderServlet?method=checkout">去结账</a></span>
		</div>

	</div>
	<!--页脚静态包含-->
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>