<%@page import="java.util.ArrayList"%>
<%@page import="ebookshop.beans.Comment"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="ebookshop.beans.Book" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<%
	String base = (String)application.getAttribute("base");
	String imageURL = (String)application.getAttribute("imageURL");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Details</title>
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css" />
<script src="/eBookShop/js/ebookshop.js" type="text/javascript"></script>
</head>
<body>
<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true" />
	<div class="container">
	<jsp:include page="LeftMenu.jsp" flush="true"/>
	<div class="content">
		<h2>Book Details</h2>
		<%
			try{
				int bookId = Integer.parseInt(request.getParameter("bookId"));
				Book book = dataManager.getBookDetails(bookId);
				if(book != null){
		%>
			<input type="hidden" id="isbn" value="<%=book.getISBN() %>" />
			<table>
				<tr>
					<td>
						<input type="image" src="<%=imageURL + "rdfmeta.gif"%>" 
						onclick="downloadRDFDocument('<%=book.getNomLivre()%>','<%=book.getNomLivre().replace(" ", "_")%>')"/>
						<br/>
						<img src="<%=book.getImage()%>"/> 
					</td>
					<td>
						<table>
							<tr>
								<td>Name</td>
								<td><%=book.getNomLivre()%></td>
							</tr>
							<tr>
								<td>Author</td>
								<td><%=book.getAuteurLivre() %></td>
							</tr>
							<tr>
								<td>Publisher</td>
								<td><%=book.getEditeurLivre() %></td>
							</tr>
							<tr>
								<td>Price</td>
								<td><span style="color: #c76200;"><%= "$  " + book.getPrixLivre()%></span></td>
							</tr>
							<tr>
								<td>ISBN</td>
								<td><%=book.getISBN() %></td>
							</tr>
							<tr>
								<td>Availability</td>
								<td><%=dataManager.getBookAvailability(book.getIdLivre()) %></td>
							</tr>
							<tr>
								<td>Rating</td>
								<td>
									<img width="90" height="18" src="<%=imageURL + dataManager.getRateImage(book.getIdLivre())%>.jpg"/>
									<%=dataManager.getNumCommentByBookId(book.getIdLivre()) %> customer(s) rate(s)
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<a href="<%=base%>?action=addItem&bookId=<%=book.getIdLivre()%>">
										<input type="button" value="Add To Cart"/>
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td><b>Overview</b></td>
				</tr>
				<tr>
					<td style="text-align: justify;" colspan="2"><%=book.getResume() %></td>
				</tr>
			</table>
<% 
				}
			}
			catch (Exception e){
				%> <p class="error">Invalid Book Identifier!</p> <%
			}
%>
		<br/>
		<%
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			ArrayList<Comment> comments	= dataManager.getValidComments(bookId);
			int numberComment = comments.size();
		%>
			<select id="referenceComment">
				<option value="1">Website 1</option>
				<option value="2">Magento</option>
			</select>
			<input type="button" value="Reference comment" onclick="getExtraComments()" />
			<div id="extraComment"></div>
			<h4>Total: <%=numberComment + " " %> comment(s)</h4>
		<%
			if(comments != null){
				Iterator<Comment> iterator = comments.iterator();
				while(iterator.hasNext()){
					Comment comment = iterator.next();
		%>
		<div>
			<table>
				<tr>
					<td><p><b><%=comment.getPersonneCommentaire()%></b></p></td>
					<td><%=comment.getPoint() %> / 10 points</td>
					<td style="text-align: right;"><p><i><%=comment.getEmailCommentaire() %></i></p></td>
				</tr>
				<tr>
					<td colspan="3"><q><i><%=comment.getContenu() %></i></q></td>
				</tr>
			</table>
			<hr>
		</div>	
		<%
				}
			}
		%>
		<br/>
		<input type="hidden" id="bookId" value="<%=Integer.parseInt(request.getParameter("bookId"))%>"/>
		<h4>Comments</h4>
		<input type="button" id="buttonComment" value="+" onclick="getCommentBox()"/>
		<div class="comment_box" id="comment_box"></div>	
	</div>
	</div>
</div>

</body>
</html>