<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<% String base = (String)application.getAttribute("base"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css"/>
<title>Login Page</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
			<div class="content">
				<h3>Login</h3>
				<form>
					<input type="hidden" name="action" value="checkLogin" />
					<table>
						<tr>
							<td>User name </td>
							<td>
								<input type="text" id="userName" name="userName" value="" required="required"/>
							</td>		
						</tr>
						<tr>
							<td>Password</td>
							<td>
								<input type="password" id="passwd" name="passwd" value="" required="required"/>
							</td>		
						</tr>
					</table>
					<input type="submit" value="Login"/> <br/>
					<a href="<%=base%>?action=register">New customer</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>