<%@page import="ebookshop.beans.Comment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<%
	int bookId = Integer.parseInt(request.getParameter("bookId"));
	String name = request.getParameter("name").toString();
	String email = request.getParameter("email").toString();
	String point = request.getParameter("point").toString();
	String review = request.getParameter("review").toString();
	
	Comment comment = new Comment();
	comment.setPersonneCommentaire(name);
	comment.setEmailCommentaire(email);
	comment.setPoint(Float.parseFloat(point));
	comment.setIdLivreCommentaire(bookId);
	comment.setContenu(review);

	int result = dataManager.insertComment(comment);
	out.println(result);
%>