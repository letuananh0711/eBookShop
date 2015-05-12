<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Iterator" %>
<%@page import="java.util.Enumeration" %>
<%@ page import="ebookshop.beans.Customer" %>
<%@ page import="ebookshop.beans.CartItem" %>

<%
	String base = (String)application.getAttribute("base");
	String imageURL = (String)application.getAttribute("imageURL");
%>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css" />
<title>Checkout</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true" />
	<div class="container">
	<jsp:include page="LeftMenu.jsp" flush="true"/>
		<div class="content">
		<h2>Checkout</h2>
		<form>
      	<input type="hidden" name="action" value="orderConfirmation"/>
<%
		String userName = (String)session.getAttribute("userName");
		if(userName == null){
%>
		<p class="error">Please login <a href="<%=base%>?action=login">here</a> before checkout!</p>
<%
		}else
		{	
			Customer customer = new Customer();
			customer = dataManager.getCustomer(userName);
			session.setAttribute("userID", customer.getIdMembre());
			
			@SuppressWarnings("unchecked")
		  	Hashtable<String, CartItem> shoppingCart = (Hashtable<String, CartItem>)session.getAttribute("shoppingCart");
		  	if (shoppingCart != null && !shoppingCart.isEmpty()){
	%>
			<table>
				<tr>
					<th>Order Details</th>
				</tr>
				<tr>
					<td>Title</td>
					<td>Author</td>
					<td>Price</td>
					<td>Quantity</td>
					<td>Subtotal</td>
				</tr>
	<%
			Enumeration<CartItem> enumList = shoppingCart.elements();
			float itemPrice = 0;
			float totalPrice = 0;
			while(enumList.hasMoreElements()){
				CartItem item = enumList.nextElement();
				itemPrice = Integer.parseInt(item.getQuantiteLivre()) * item.getPrixLivre();
				totalPrice += itemPrice;
		
	%>
				 <tr>
				 	<td><%=item.getNomLivre() %></td>
				 	<td><%=item.getAuteurLivre() %></td>
				 	<td><%=item.getPrixLivre() %></td>
				 	<td><%=item.getQuantiteLivre() %></td>
				 	<td><%=itemPrice %></td>
				 </tr>
	<%
			}
	%>	
				<tr>
					<td colspan="5" id="total">Total price: $ <%=totalPrice %></td>
					<td class="total">&nbsp;</td>
				</tr>
				<tr>
					<th>Customer Details</th>
				</tr>		
				<tr>
					<td>Customer ID</td>
					<td><%=customer.getIdMembre() %></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><%=customer.getNomMembre() + " " + customer.getPrenomMembre() %></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><%=customer.getEmailMembre() %></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><%=customer.getTelephone() %></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><%=customer.getAdresse() %></td>
				</tr>
				<tr>
	          		<td>&nbsp;</td>
	          		<td><input type="submit" value="Confirm Order"/></td>
	          	</tr>
			</table>
<%
	  	}
	  	else{
%>			
			<p class="error">ERROR: You can't check out an empty shopping cart!</p>
<%
	  	}
	}
%>
		</form>						
		</div>
		</div>
	</div>
</body>
</html>