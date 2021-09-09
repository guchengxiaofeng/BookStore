<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/pages/common/head.jsp"%>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>发货</td>

				
			</tr>
			<c:forEach items="${requestScope.page.items}" var="order">
				<tr>
					<td>${order.create_Time}</td>
					<td>${order.price}</td>
					<td>${order.status==0 ? "未发货":"已发货"}</td>
					<td><a href="http://172.23.137.217:8080/BookStore/pages/order/orderDetail.jsp?orderId=${order.order_Id}">查看详情</a></td>
					<td><a href="manager/orderServlet?action=sendOrder&orderId=${order.order_Id}">发货</a></td>
<%--					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}">删除</a></td>--%>
				</tr>
			</c:forEach>
			

		</table>
		<%@ include file="/pages/common/page_nav.jsp"%>

	</div>

	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>