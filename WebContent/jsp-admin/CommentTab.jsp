<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ebookshop.beans.Comment"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>Comment tab</title>
</head>
<body>
	<jsp:include page="AdminTopMenu.jsp"></jsp:include>
	<h2>Comment list</h2>
	<table id="box-table-a">
		<tr>
			<th>ID</th>
			<th>Commentator</th>
			<th>Email</th>
			<th>Comment</th>
			<th>Point</th>
			<th>Book ID</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<%
			ArrayList<Comment> listComment = dataManager.getComments();
			Iterator<Comment> iterator = listComment.iterator();
			while(iterator.hasNext()){
				Comment comment = iterator.next();
		%>
			<tr>
				<td><%=comment.getIdCommentaire()%></td>
				<td><%=comment.getPersonneCommentaire()%></td>
				<td><%=comment.getEmailCommentaire()%></td>
				<td><%=comment.getContenu()%></td>
				<td><%=comment.getPoint()%></td>
				<td><%=comment.getIdLivreCommentaire()%></td>
				<td><%=comment.getEtatConmmentaire()%></td>
				<td><input type="button" value="Action" /></td>
			</tr>
		<%		
			}
		%>
	</table>
	<div id="pageNavPosition"></div>
	<script type="text/javascript">
        var pager = new Pager('box-table-a', 8); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
   </script>
</body>
</html>