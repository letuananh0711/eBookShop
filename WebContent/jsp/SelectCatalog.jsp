<%@page language="java" contentType="text/html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="ebookshop.beans.Book"%>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />
<% String base = (String)application.getAttribute("base");%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css" />
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>Browse Catalog</title>
</head>
<body>
<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
		
	<%
		String categoryID = request.getParameter("id");
		String categoryName = null;
		
		if(categoryID != null && !categoryID.trim().equals("")){
			try{
				categoryName = dataManager.getCategoryName(Integer.parseInt(categoryID));
			} catch(NumberFormatException e){} 
		}
		
		if(categoryName != null){
	%>
		<div class="content">
	      	<h2>Select Catalog</h2>
	      	<p>Category: <strong><%=categoryName%></strong></p>
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
	 	ArrayList<Book> books = dataManager.getBooksInCategory(Integer.parseInt(categoryID));
	 	Iterator<Book> iterator = books.iterator();
	 	while(iterator.hasNext()){
	 		Book book = (Book)iterator.next();
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
	        var pager = new Pager('result', 10); 
	        pager.init(); 
	        pager.showPageNav('pager', 'pageNavPosition'); 
	        pager.showPage(1);
	   </script>
	 	</div>
	 <%
		}
		else{
			%><p class="error">Invalid category!</p><%
		}
	 %>
 	</div>
</div>
</body>
</html>