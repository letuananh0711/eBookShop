<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Iterator"%>
<%@page import="ebookshop.beans.Book"%>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<%
	String base = (String)application.getAttribute("base");
	String imageURL = (String)application.getAttribute("imageURL");
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css" />
	<title>Book Shop Online</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/jsp/TopMenu.jsp" flush="true"/>
		<div class="container">
			<jsp:include page="/jsp/LeftMenu.jsp" flush="true"/>
			<div class="content">
				<h4 style="color: red;">New Books</h4>
				<table>
					<tr>
				<%
					ArrayList<Book> books = dataManager.getNewBooks();
					Iterator<Book> iterator = books.iterator();
					while(iterator.hasNext()){
						Book book = iterator.next();
				%>
						<td width="50px">
							<a class="link1" href="<%=base%>?action=bookDetails&bookId=<%=book.getIdLivre()%>"><img src="<%=book.getImage() %>" width="89" height="135" /></a>
							<img src="<%=imageURL%>icon_new.gif" />
							<p><%=book.getNomLivre() %></p>
							<p><%=book.getAuteurLivre() %></p>
							<p><img width="90" height="18" src="<%=imageURL + dataManager.getRateImage(book.getIdLivre())%>.jpg"/> (<%=dataManager.getNumCommentByBookId(book.getIdLivre()) %>)</p>
							<p style="color: red;">$ <%=book.getPrixLivre() %></p>					
						</td>
				
				<%
					}
				%>
					</tr>
				</table>
				
				<h4 style="color: red;">Top rate</h4>
				<table>
					<tr>
				<%
					ArrayList<Book> bookList = dataManager.getTopRateBooks();
					Iterator<Book> iterator1 = bookList.iterator();
					while(iterator1.hasNext()){
						Book book = iterator1.next();
				%>
						<td width="50px">
							<a class="link1" href="<%=base%>?action=bookDetails&bookId=<%=book.getIdLivre()%>"><img src="<%=book.getImage() %>" width="89" height="135" /></a>
							<img src="<%=imageURL%>icon_hot.gif" />
							<p><%=book.getNomLivre() %></p>
							<p><%=book.getAuteurLivre() %></p>
							<p><img width="90" height="18" src="<%=imageURL + dataManager.getRateImage(book.getIdLivre())%>.jpg"/> (<%=dataManager.getNumCommentByBookId(book.getIdLivre()) %>)</p>
							<p style="color: red;">$ <%=book.getPrixLivre() %></p>					
						</td>
				
				<%
					}
				%>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>