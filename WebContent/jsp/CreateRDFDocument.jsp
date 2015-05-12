<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<%
	String bName = request.getParameter("bookName").toString();
	int result = dataManager.createRDFDocument(bName);
	if(result == 1){
		out.println(1);
	}
	else{
		out.println("RDF Document was not created!");
	}
	
%>
