<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ebookshop.beans.BookRDF"%>
<%@page import="ebookshop.model.BookRDFPeer" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList" %>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />
<%
	String base = (String)application.getAttribute("base");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>Admin Search Book</title>
</head>
<body>
	<div>
		<jsp:include page="AdminTopMenu.jsp"></jsp:include>
		<h2>Search for new book</h2>
		<form>
			<input type="hidden" name="action" value="searchBook" />
			<input type="text" id="keyWord" name="keyWord" required="required" />
			<input type="submit" value="Search" />
		</form>
<%
	String keyWord = request.getParameter("keyWord");
	if(keyWord != null){
%>
	<h2>Search Results</h2>
	<table id="box-table-a">
		<tr>
			<th>Name</th>
			<th>Category</th>
			<th>ISBN</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
	<%
		
			ArrayList<BookRDF> books = dataManager.getBookFromDbPedia(keyWord);
			Iterator<BookRDF> iterator = books.iterator();
			while(iterator.hasNext()){
				BookRDF book = iterator.next();
	%>
			<tr>		
				<td><%=book.getBookName().replace("@en", "") %></td>
				<td><%=book.getBookCategory().replace("@en", "") %></td>
				<td><%=book.getISBN().replace("@en", "") %></td>
				<td><%=book.getBookAuthor().replace("@en", "")%></td>
				<td><%=book.getBookPublisher().replace("@en", "") %></td>
				<td><%=book.getBookDescription().replace("@en", "") %></td>
				<td>
					<a href="<%=base%>?action=newBookRDF&bookName=<%=book.getBookName().replace("@en", "")%>
						&isbn=<%=book.getISBN().replace("@en", "")%>
						&bookAuthor=<%=book.getBookAuthor().replace("@en", "")%>
						&bookPublisher=<%=book.getBookPublisher().replace("@en", "")%>
						&bookDescription=<%=book.getBookDescription().replace("@en", "")%>">
						<input type="button" value="Create book" />
					</a>
				</td>
			</tr>
	<%	
			}
		}
		else{
			out.println("Please fill out keyword!");
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