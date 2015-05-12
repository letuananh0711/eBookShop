<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Enumeration" %>
<%@page import="java.util.Hashtable" %>
<%@page import="ebookshop.beans.CartItem" %>
<%@page import="ebookshop.beans.Book" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>
<%
	String base = (String)application.getAttribute("base");
	
	@SuppressWarnings("unchecked")

	Hashtable<String, CartItem> shoppingCart = (Hashtable<String, CartItem>)session.getAttribute("shoppingCart");
	if(shoppingCart == null){
		shoppingCart = new Hashtable<String, CartItem>(10);
	}
	
	String action = request.getParameter("action");
	
	// Add book to cart
	if(action != null && action.equals("addItem")){
		try{
			String bookId = request.getParameter("bookId");
			Book book = dataManager.getBookDetails(Integer.parseInt(bookId));
			if(book != null){
				CartItem item = new CartItem(book, 1);
				shoppingCart.remove(bookId);
				shoppingCart.put(bookId, item);
				
				session.setAttribute("shoppingCart", shoppingCart);
			}
		}
		catch (Exception e){
			out.println("Error adding the selected book to the shopping cart!");
		}
	}
	
	// Update quatity of book in the cart
	if(action != null && action.equals("updateItem")){
		try{
			String bookId = request.getParameter("bookId");
			String quantity = request.getParameter("quantity");
			CartItem item = shoppingCart.get(bookId);
			if(item != null){
				item.setQuantiteLivre(quantity);
			}
		}
		catch (Exception e){
			out.println("Error updating shopping cart!");
		}
	}
	
	// Delete an item in the cart
	if(action != null && action.equals("deleteItem")){
		try{
			String bookId = request.getParameter("bookId");
			shoppingCart.remove(bookId);
		}
		catch (Exception e){
			out.println("Error deleting the selected item from the shopping cart!");
		}
	}
	
%>
<html>
<head>
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css"/>
<script src="/eBookShop/js/ebookshop.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
</head>
<body>
<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
		
		
	<%
		if(!shoppingCart.isEmpty()){
	%>
		<div class="content">
			<h2>Shopping Cart</h2>
			<table>
				<tr>
					<th>Title</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Subtotal</th>
					<th>Delete</th>
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
				 	<td>
				 		<form>
				 			<input type="hidden" name="action" value="updateItem" />
				 			<input type="hidden" name="bookId" value="<%=item.getIdLivre()%>"/>
				 			<input type="text" id="quantity" name="quantity" value="<%=item.getQuantiteLivre()%>" size="1" onblur="isNumber()"/>
				 			<input type="submit" value="Update" />
				 		</form>
				 	</td>
				 	<td><%=itemPrice %></td>
				 	<td>
				 		<form>
				 			<input type="hidden" name="action" value="deleteItem" />
				 			<input type="hidden" name="bookId" value="<%=item.getIdLivre() %>"/>
				 			<input type="submit" value="Delete"/>
				 		</form>
				 	</td>
				 </tr>
	<%
		}
	%>	
				<tr>
					<td colspan="5" id="total">Total price: $ <%=totalPrice %></td>
					<td class="total">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="6" class="center">
	      				<a class="link1" href="<%=base%>?action=checkOut">
	      					<input type="button" value="Check out"/>
	      				</a>
	      				<a class="link1" href="<%=base%>"><input type="button" value="Continue shopping"/></a>
	          		</td>
				</tr>
			</table>
		</div>
	<%	
			}
		else{
			%><p class="info">The Shopping cart is empty!</p><%
		}
	%>
	</div>
</div>	
</body>
</html>