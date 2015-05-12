<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ebookshop.beans.Customer" %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<%
	String userName = request.getParameter("userName").toString();
	String result = "";
	
	if(dataManager.isUserNameExist(userName) == 1){
		result = "User is already exist!";
	}
	out.println(result);
%>

