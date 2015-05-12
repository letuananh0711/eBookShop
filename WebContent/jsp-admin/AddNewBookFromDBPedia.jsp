<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="ebookshop.beans.Category"%>
<%@page import="ebookshop.model.CategoryPeer"%>
<%@page import="ebookshop.beans.BookRDF"%>
<%@page import="ebookshop.model.BookRDFPeer" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<title>Add New Book from DBPedia</title>
</head>
<body>
	<div>
		<%
			String adminUserName = (String)session.getAttribute("adminUserName");
			String bookName = request.getParameter("bookName").toString();
			//String bookCategory = request.getParameter("bookCategory").toString();
			String isbn = request.getParameter("isbn").toString();
			String bookAuthor = request.getParameter("bookAuthor").toString();
			String bookPublisher = request.getParameter("bookPublisher").toString();
			String bookDescription = request.getParameter("bookDescription").toString();
		%>
		<jsp:include page="AdminTopMenu.jsp"></jsp:include>
		<h2>New Book Information</h2>
		<form>
			<input type="hidden" name="action" value="createBook"/>
			<table>	
				<tr>
					<th>Book name</th>
					<td>
						<input type="text" name="bookName" value="<%=bookName%>" required="required"/>
					</td>
				</tr>
				<tr>
					<th>Category</th>
					<td>
						<select name="bookCategory">
						<%
							ArrayList<Category> categories = dataManager.getCategories();
							Iterator<Category> iterator = categories.iterator();
							while(iterator.hasNext()){
								Category category = iterator.next();
						%>
							<option value="<%=category.getIdGenreLivre()%>"><%=category.getNomGenreLivre()%></option>
						<%
							}
						%>
						</select>
					</td>
				</tr>
				<tr>
					<th>ISBN</th>
					<td>
						<input type="text" name="bookISBN" value="<%=isbn.replace("ISBN ", "")%>" required="required"/>
					</td>
				</tr>
				<tr>
					<th>Author</th>
					<td>
						<input type="text" name="bookAuthor" value="<%=bookAuthor%>" required="required"/>
					</td>
				</tr>
				<tr>
					<th>Publisher</th>
					<td>
						<input type="text" name="bookPublisher" value="<%=bookPublisher%>" required="required"/>
					</td>
				</tr>
				<tr>
					<th>Publisher's page</th>
					<td>
						<input type="text" name="publisherPage" value="http://google.com"/>
					</td>
				</tr>
				<tr>
					<th>Description</th>
					<td>
						<textarea rows="10" cols="60" name="bookDescription"><%=bookDescription%></textarea>
					</td>
				</tr>
				<tr>
					<th>Quantity</th>
					<td>
						<input type="text" name ="bookQuantity" required="required"/>
					</td>
				</tr>
				<tr>
					<th>Price</th>
					<td>
						<input type="text" name="bookPrice" required="required"/>USD
					</td>
				</tr>
				<tr>
					<th>Image</th>
					<td>
						<input type="text" name="bookImage" required="required"/>
					</td>
				</tr>
			</table>
			<input type="submit" value="Create" />
		</form>
	</div>
</body>
</html>