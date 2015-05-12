<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<% String base = (String)application.getAttribute("base"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css"/>
<title>Login status</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
			<div class="content">
			<%
				String userName = request.getParameter("userName");
				String passwd = request.getParameter("passwd");
				
				int result = 0;
				
				if(userName != null && passwd != null)
				{
					result = dataManager.checkUserLogin(userName, passwd);
				}
				
				if(result == 1){
					session.setAttribute("userName", userName);
					%>
					<p class = "info">
						Login successfully. <a href="<%=base%>?action=goHomePage">Click here</a> to go to home page!
					</p>
					<%
				}
				else{
					%>
					<p class="error">
						Login fail. Please try again by clicking <a href="<%=base%>?action=login">here</a>!
					</p>
					<%
				}
				
			%>
			</div>
		</div>
	</div>
</body>
</html>