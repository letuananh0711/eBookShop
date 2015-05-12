<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ebookshop.beans.Category" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<script src="/eBookShop/js/ebookshop.js" type="text/javascript"></script>
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>Book Category Tab</title>
</head>
<body>
	<jsp:include page="AdminTopMenu.jsp"></jsp:include>
		
	<h2>Category</h2>
		<input type="text" id="categoryName" placeholder="Category name"/>
		<input type="text" id="categoryDescription" placeholder="Description for category" />
		<input type="button" value="Add" onclick="addNewCategory()" />
	
	<h3>List</h3>
	<table id="box-table-a">	
		<tr>
			<th>ID</th>
			<th>Category Name</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<%
			ArrayList<Category> categoryList = dataManager.getCategories();
			Iterator<Category> iterator1 = categoryList.iterator();
			while(iterator1.hasNext()){
				Category category = iterator1.next();
		%>
		<tr>
			<td><%=category.getIdGenreLivre()%></td>
			<td><%=category.getNomGenreLivre()%></td>
			<td><%=category.getDescription()%></td>
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
</body>
</html>