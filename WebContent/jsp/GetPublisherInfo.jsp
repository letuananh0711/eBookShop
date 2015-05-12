<%@page import="java.util.Iterator"%>
<%@page import="ebookshop.beans.Publisher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<%
	String publisherName = request.getParameter("pName").toString();

	Publisher publisher = new Publisher(); 
	
	publisher = dataManager.getPublisherInfo(publisherName);
	
	if(publisher == null){
		out.println("0");
	}
	else
	{
		String publisherInfo = "<table>"
							+ "<tr>"
							+	"<td colspan='2'><img src='" + publisher.getImage() +"' /></td>"
							+ "</tr>"
							+ "<tr>"
							+	"<td colspan='2'><q><i>" + publisher.getDescription().replace("@en", "") +"</i></q></td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Publisher name</td>"
							+ 	"<td>" + publisher.getNamePublisher() +	"</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Founder</td>"
							+ 	"<td>" + publisher.getFounder().replace("@en", "") + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Foundation year</td>"
							+ 	"<td>" + publisher.getYearFounded().replace("^^http://www.w3.org/2001/XMLSchema#int", "")  + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Headquarter</td>"
							+ 	"<td>" + publisher.getHeadquaterName().replace("@en", "") + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Home page</td>"
							+ 	"<td>"
							+ "<a href='" + publisher.getHomepage() + "'>" + publisher.getHomepage() + "</a>"
							+	"</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Publication</td>"
							+ 	"<td>" + publisher.getPublication().replace("@en", "") + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Books</td>"
							+ 	"<td>";
		Iterator<String> iterator = publisher.getBooks().iterator();
		Iterator<String> iterator1 = publisher.getLinkBooks().iterator();
		
		while(iterator.hasNext() && iterator1.hasNext()){
			String bookName = iterator.next();
			String linkBook = iterator1.next();
			publisherInfo = publisherInfo + "<a href='" + linkBook + "' target='_blank'/>" + bookName.replace("@en", "") + "<br/>";
		}
		publisherInfo = publisherInfo	+ "</td>"
							+ "</tr>" 
							+ "<tr>"
							+ 	"<td>Same as</td>"
							+ 	"<td>"
							+ "<a href='" + publisher.getSameAs() + "' target='_blank'>" + publisher.getSameAs() + "</a>"
							+	"</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>See also</td>"
							+ 	"<td>"
							+ "<a href='" + publisher.getSeeAlso() + "' target='_blank'>" + publisher.getSeeAlso() + "</a>"
							+	"</td>"
							+ "</tr>"
							+ "</tr>"
							+ "</table>";
		
		out.println(publisherInfo);
	}
	
%>