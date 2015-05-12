<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<title>Administrator Login Page</title>
</head>
<body>
	<form id="login">
		<input type="hidden" name="action" value="checkAdminLogin"/>
		<input type="text" id="adminUserName" name="adminUserName" required="required" placeholder="Account admin"/>
		<input type="password" id="passWdAdmin" name="passWdAdmin" required="required" placeholder="Password"/>
		<input type="submit" value="Login" />
	</form>
</body>
</html>