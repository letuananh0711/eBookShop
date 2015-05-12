<%@page import="ebookshop.beans.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<%
	String cName = request.getParameter("cName").toString();
	String cDes = null;
	try{
		cDes = request.getParameter("cDes").toString();
	}
	catch (Exception e) {
	}
	
	Category category = new Category();
	
	category.setNomGenreLivre(cName);
	category.setDescription(cDes);
	
	int result = dataManager.insertCategory(category);
	
	out.println(result);
	
%>