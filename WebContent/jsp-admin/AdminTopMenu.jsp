<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String base = (String)application.getAttribute("base");
	String imageURL = (String)application.getAttribute("imageURL");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<title>Admin Top Menu</title>
</head>
<body>
		<%
			String adminUserName = (String)session.getAttribute("adminUserName");
		%>
		<p>Welcome administrator!</p>
		<div style="padding-left: 500;">
			<table style="text-align: center;">
				<tr>
					<td><a href="<%=base%>?action=bookTab"><img src="<%=imageURL%>icon-book.png"  width="84" height="84"/></a></td>
					<td><a href="<%=base%>?action=categoryTab"><img src="<%=imageURL%>icon-category.png" width="84" height="84"/></a></td>
					<td><a href="<%=base%>?action=commentTab"><img src="<%=imageURL%>icon-comment.png" width="84" height="84"/></a></td>
					<td><a href="<%=base%>?action=orderTab"><img src="<%=imageURL%>icon-order.png" width="84" height="84"/></a></td>
					<td><a href="<%=base%>?action=userTab"><img src="<%=imageURL%>icon-user.png" width="84" height="84"/></a></td>
				</tr>
				<tr>
					<td>Book</td>
					<td>Category</td>
					<td>Comment</td>
					<td>Order</td>
					<td>User</td>
				</tr>
			</table>
		</div>
</body>
</html>