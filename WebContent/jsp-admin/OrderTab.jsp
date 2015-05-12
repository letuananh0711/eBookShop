<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="ebookshop.beans.Order" %>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>Order Tab</title>
</head>
<body>
	<jsp:include page="AdminTopMenu.jsp"></jsp:include>
	<h2>Order list</h2>
	<table id="box-table-a">
		<tr>
			<th>ID</th>
			<th>Order Date</th>
			<th>Member</th>
			<th colspan="2">Action</th>
		</tr>
		<%
			ArrayList<Order> orderList = dataManager.getOrders();
			Iterator<Order> iterator = orderList.iterator();
			while(iterator.hasNext()){
				Order order = iterator.next();
		%>
		<tr>
			<td><%=order.getIdCommande() %></td>
			<td><%=order.getDateCommande() %></td>
			<td><%=order.getIdMembre() %></td>
			<td>
				<input type="button" value="Detail"/>
			</td>
			<td>
				<input type="button" value="Delete"/>
			</td>
		</tr>
		<%
			}
		%>
		<tr></tr>
	</table>
	<div id="pageNavPosition"></div>
	<script type="text/javascript">
        var pager = new Pager('box-table-a', 8); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
   </script>
</body>
</html>