<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css"/>
<title>Logout Page</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
			<div class="content">
				<%
					session.removeAttribute("userName");
					session.invalidate();
				%>
				<p class="info">Logout was done successfully!</p>
			</div>
		</div>
	</div>
</body>
</html>