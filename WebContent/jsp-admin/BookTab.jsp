<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ebookshop.beans.Category" %>
<%@ page import="ebookshop.beans.Book" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<script src="/eBookShop/js/ebookshop.js" type="text/javascript"></script>
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>Book Tab</title>
</head>
<body>
	<div>
		<jsp:include page="AdminTopMenu.jsp"></jsp:include>
		<h2>Book</h2>
			<input type="text" id="bName" placeholder="Book name" />
			<input type="text" id="bAuthor" placeholder="Author" />
			<input type="text" id="bPublisher" placeholder="Publisher"/>
			<input type="text" id="bQuantity" size="3" placeholder="Amount"/>
			<input type="text" id="bPrice" size="3" placeholder="Price" />
			<input type="text" id="bImage" placeholder="Image link" />
			<textarea cols="50" rows="5" style="resize: none; position: relative; top: 40;" id="bDes" placeholder="Something about this book..."></textarea>
			<input type="text" id="bISBN" size="11" placeholder="ISBN number"/>
			<select id="bCategory">
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
			<input type="button" value="Add" onclick="addNewBook()" />
		<h3>List</h3>
		<form>
			<input type="hidden" name="action" value="searchBook" />
			<input type="submit" value="Search for new" />
		</form>
		<table id="box-table-a">
			<tr>
				<th>ID</th>
				<th>Book Name</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Image link</th>
				<th>Description</th>
				<th>ISBN</th>
				<th>ID Category</th>
				<th>Action</th>
			</tr>
			<%
				ArrayList<Book> bookList = dataManager.getBooks();
				Iterator<Book> iterator2 = bookList.iterator();
				while(iterator2.hasNext()){
					Book book = iterator2.next();
			%>
			<tr>
				<td><%=book.getIdLivre()%></td>
				<td><%=book.getNomLivre()%></td>
				<td><%=book.getAuteurLivre()%></td>
				<td><%=book.getEditeurLivre()%></td>
				<td><%=book.getQuantiteLivre()%></td>
				<td><%=book.getPrixLivre()%></td>
				<td><img width="90" height="135" src="<%=book.getImage()%>"/></td>
				<td><%=book.getResume()%></td>
				<td><%=book.getISBN()%></td>
				<td><%=book.getIdGenreLivre()%></td>
				<td>
					<input type="button" value="Action" />
				</td>
			</tr>
			<%
				}
			%>
		</table>
		<div id="pageNavPosition"></div>
		<script type="text/javascript">
	        var pager = new Pager('box-table-a', 5); 
	        pager.init(); 
	        pager.showPageNav('pager', 'pageNavPosition'); 
	        pager.showPage(1);
	   </script>
	</div>
</body>
</html>