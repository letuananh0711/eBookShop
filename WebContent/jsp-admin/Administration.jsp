<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String admin = (String)session.getAttribute("adminUserName");
%>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator</title>
</head>
<body>
	<div>
		<p>Welcome <%=admin%> (Administrator)!</p><br/>
	</div>
</body>
</html>