<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	String base = (String)application.getAttribute("base");
	String imageURL = (String)application.getAttribute("imageURL");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<title>Checking Admin Login</title>
</head>
<body>
	<%
		String adminUserName = request.getParameter("adminUserName");
		String passWdAdmin = request.getParameter("passWdAdmin");
		
		if(adminUserName.toLowerCase().trim().equals("admin") &&
				passWdAdmin.toLowerCase().trim().equals("admin")){
			session.setAttribute("adminUserName", adminUserName);
			%>
			<jsp:include page="AdminTopMenu.jsp"></jsp:include>
			<%
		}
		else{
			%>
			<div id="login">
				<p style="color: white; text-align: center;">Invalid admin user name or password.<a href="<%=base%>?action" style="color: red;"> Try again!</a></p>
			</div>
			<%
		}
	%>
</body>
</html>