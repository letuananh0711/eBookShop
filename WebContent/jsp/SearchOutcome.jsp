<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="ebookshop.beans.Book" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<% String base = (String)application.getAttribute("base"); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css"/>
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>Search Result</title>
</head>
<body>
<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
		<%
			String keyword = request.getParameter("keyword");
			if(keyword != null && !keyword.trim().equals("")){
				%>
				<div class="content">
					<h2>Search results</h2>
					<table id="result">
						<tr>
							<th>Title</th>
							<th>Author</th>
							<th>Publisher</th>
							<th>Price</th>
							<th>ISBN</th>
							<th>Details</th>
						</tr>
						<%
							ArrayList<Book> books = dataManager.getSearchResults(keyword);
							Iterator<Book> iterator = books.iterator();
							while(iterator.hasNext()){
								Book book = iterator.next();
						%>
						<tr>
	            			<td><%=book.getNomLivre()%></td>
	            			<td><%=book.getAuteurLivre()%></td>
	            			<td><%=book.getEditeurLivre()%></td>
	            			<td><%=book.getPrixLivre()%></td>
	            			<td><%=book.getISBN()%></td>
	            			<td><a class="link1" href="<%=base%>?action=bookDetails&bookId=<%=book.getIdLivre()%>">Details</a></td>
		        		</tr>
						<%
							}
						%>
					</table>
					<div id="pageNavPosition"></div>
					<script type="text/javascript">
				        var pager = new Pager('result', 7); 
				        pager.init(); 
				        pager.showPageNav('pager', 'pageNavPosition'); 
				        pager.showPage(1);
				   </script>
				</div>
				<%
			}
			else{
				%> <p class="error">Invalid keyword!</p><%
			}
		%>
	</div>
</div>
</body>
</html>