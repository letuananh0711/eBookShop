<%@page import="java.util.Iterator"%>
<%@page import="ebookshop.beans.ExtraComment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="dataManager" scope="page" class="ebookshop.model.DataManager"/>

<%
	String isbn = request.getParameter("isbn").toString();
	String site = request.getParameter("site").toString();
	
	String result;
	
	if(site.equals("2")){
		result = "<table>"
				+ "<th>Point (/5)</th>"
				+ "<th>Comment</th>";
	}
	else{
		result = "<table>"
				+ "<th>Point (/10)</th>"
				+ "<th>Comment</th>";
	}
	
	ArrayList<ExtraComment> extraComments = dataManager.getExtraComments(isbn, site);
	if(extraComments == null){
		out.println("0");	
	}
	else{
		Iterator<ExtraComment> iterator = extraComments.iterator();
		while(iterator.hasNext()){
			ExtraComment extraComment = iterator.next();
			result = result
					+ "<tr>"
						+ "<td>"
						+ extraComment.getPoint()
						+ "</td>"
						+ "<td>"
						+ extraComment.getContentComment()
						+ "</td>"
					+ "</tr>";
		}
		
		result = result	+ "</table>";
	
		out.println(result);
	}
%>