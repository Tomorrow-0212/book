<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<!--base标签、jQuery、CSS样式静态包含-->
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<!--图书管理静态包含-->
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/bookManagerServlet" method="post">
				<input type="hidden" name="method" value="addBook">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" placeholder="书籍名称"/></td>
						<td><input name="price" type="text" placeholder="价格"/></td>
						<td><input name="author" type="text" placeholder="作者"/></td>
						<td><input name="sales" type="text" placeholder="销量"/></td>
						<td><input name="stock" type="text" placeholder="库存"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		<!--页脚静态包含-->
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>