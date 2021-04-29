<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<!--base标签、jQuery、CSS样式静态包含-->
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteClass").click(function () {
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<!--图书管理静态包含-->
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.pageList.pageList}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookManagerServlet?method=echoBook&id=${book.id}&pageNo=${requestScope.pageList.pageNo}">修改</a></td>
					<td><a class="deleteClass"
						   href="manager/bookManagerServlet?method=deleteBook&id=${book.id}&pageNo=${requestScope.pageList.pageNo}">删除
						</a>
					</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.pageList.allPage}">添加图书</a></td>
			</tr>
		</table>
		<%--分页静态包含--%>
		<%@include file="/pages/common/page.jsp"%>
	</div>
	<!--页脚静态包含-->
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>