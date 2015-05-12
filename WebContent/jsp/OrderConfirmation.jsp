<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Hashtable"%>
<%@page import="ebookshop.beans.CartItem"%>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css" />
<title>Order Confirmation</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
			<div class="content">
	<%
		try{	
			String userID = (String)session.getAttribute("userID").toString();
	    @SuppressWarnings("unchecked")
	    Hashtable<String, CartItem> cart = (Hashtable<String, CartItem>)session.getAttribute("shoppingCart");
	    long orderId = dataManager.insertOrder(Integer.parseInt(userID), cart);
	    if (orderId > 0L) {
	      session.removeAttribute("shoppingCart");
	%>
	      <p class="info">
	        Thank you for your purchase.<br/>
	        Your Order Number is: <%=orderId%>
	        </p>
	<%
	      }
	    else {
	      %><p class="error">Unexpected error processing the order!</p><%
	      }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	%>
			</div>
		</div>
	</div>
</body>
</html>