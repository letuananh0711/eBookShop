<%@page import="ebookshop.beans.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ebookshop.beans.BookRDF"%>
<%@page import="ebookshop.model.BookRDFPeer" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<title>Create Book RDF Document</title>
</head>
<body>
	<div>
		<%
			String adminUserName = (String)session.getAttribute("adminUserName");
		
			String bName = request.getParameter("bookName").toString();
			String bCategory = request.getParameter("bookCategory").toString();
			String bISBN = request.getParameter("bookISBN").toString();
			String bAuthor = request.getParameter("bookAuthor").toString();
			String bPublisher = request.getParameter("bookPublisher").toString();
			//String pubPage = request.getParameter("publisherPage").toString();
			String bDescription = request.getParameter("bookDescription").toString();
			String bQuantity = request.getParameter("bookQuantity").toString();
			String bPrice = request.getParameter("bookPrice").toString();
			String bImage = request.getParameter("bookImage").toString();
		%>
		<jsp:include page="AdminTopMenu.jsp"></jsp:include>
		<%
			try{
				Book book = new Book();
				book.setNomLivre(bName);
				book.setIdGenreLivre(Integer.parseInt(bCategory));
				book.setISBN(bISBN);
				book.setAuteurLivre(bAuthor);
				book.setEditeurLivre(bPublisher);
				book.setResume(bDescription);
				book.setQuantiteLivre(Integer.parseInt(bQuantity));
				book.setPrixLivre(Float.parseFloat(bPrice));
				book.setImage(bImage);
				//int result1 = dataManager.insertBookSW(bName, bCategory, bISBN, bAuthor, bPublisher, pubPage, bDescription, bImage);
				int result2 = dataManager.insertBook(book);
				//if(result1 == 1){
			%>
			<!-- <p>The RDF document of book "<%=bName%>" was insert successfully into database!</p> --> 
			<%
				//} else{
			%>
			<!-- <p>Failed! Try again. </p> -->
			<%
				//}
				if(result2 == 1){
			%>
				<p>The book "<%=bName%>" was created successfully!</p> 
			<%
				} else{
			%>
				<p>Failed! Try again. </p>
			<%
				}
			}catch (Exception e){
				
			}
			%>
	</div>
</body>
</html>