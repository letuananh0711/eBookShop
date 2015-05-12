<%@page import="org.apache.tomcat.util.net.SecureNioChannel.ApplicationBufferHandler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ebookshop.beans.Customer"  %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<% String base = (String)application.getAttribute("base"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css"/>
<title>Register Confirmation</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
			<div class="content">
	<%	
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String userName = request.getParameter("userName");
			String passwd = request.getParameter("passwd");
			
			Customer customer = new Customer();
			
			customer.setNomMembre(firstName);
			customer.setPrenomMembre(lastName);
			customer.setSexe(gender);
			customer.setDateNaissance(dob);
			customer.setEmailMembre(email);
			customer.setTelephone(phone);
			customer.setAdresse(address);
			customer.setUserName(userName);
			customer.setPasswd(passwd);
		
			int result = dataManager.insertCustomer(customer);
		
			if(result == 1){ 
	%>
			<p class="info">
				Register successfully!<br/>
				Now you can <a href="<%=base%>?action=login">login</a> for buying book!				
			</p>
	<%
			}
			else{
	%>
			<p class="error">
				Unexpected error processing the registration. Please try <a href="<%=base%>?action=register">again</a>!
			</p>
	<% 
			} 
	%>
			</div>
		</div>
	</div>
</body>
</html>