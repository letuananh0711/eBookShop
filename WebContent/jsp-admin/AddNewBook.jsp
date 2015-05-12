<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ebookshop.beans.Book" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<%
	Book book = new Book();
	book.setNomLivre(request.getParameter("bName").toString());
	book.setAuteurLivre(request.getParameter("bAuthor").toString());
	book.setEditeurLivre(request.getParameter("bPublisher").toString());
	book.setIdGenreLivre(Integer.parseInt(request.getParameter("bCategory").toString()));
	book.setImage(request.getParameter("bImage").toString());
	book.setISBN(request.getParameter("bISBN").toString());
	book.setPrixLivre(Float.parseFloat(request.getParameter("bPrice").toString()));
	book.setQuantiteLivre(Integer.parseInt(request.getParameter("bQuantity").toString()));
	book.setResume(request.getParameter("bDes").toString());
	
	int result = dataManager.insertBook(book);
	if(result == 1){
		out.println(1);
	}
	else{
		out.println(0);
	}

%>