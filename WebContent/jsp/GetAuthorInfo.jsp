<%@page import="java.util.Iterator"%>
<%@page import="ebookshop.beans.Author"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<%
	String authorName = request.getParameter("aName").toString();

	Author author = new Author();
	
	author = dataManager.getAuthorInfo(authorName);
	
	if(author == null){
		out.println("0");
	}
	else
	{
		String authorInfo = "<table>"
							+ "<tr>"
							+	"<td colspan='2'><img src='" + author.getImage() +"' /></td>"
							+ "</tr>"
							+ "<tr>"
							+	"<td colspan='2'><q><i>" + author.getDescription().replace("@en", "") +"</i></q></td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Author name</td>"
							+ 	"<td>"
							+ author.getNameAuthor()
							+	"</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Birth name</td>"
							+ 	"<td>" + author.getBirthName().replace("@en", "") + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Birthday</td>"
							+ 	"<td>" + author.getBirthDate().replace("^^http://www.w3.org/2001/XMLSchema#date", "")  + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Birth place</td>"
							+ 	"<td>" + author.getBirthCity().replace("@en", "") + ", " + author.getBirthCountry().replace("@en", "")  + "</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Home page</td>"
							+ 	"<td>"
							+ "<a href='" + author.getHomePage() + "'>" + author.getHomePage() + "</a>"
							+	"</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>Books</td>"
							+ 	"<td>";
		Iterator<String> iterator = author.getBooks().iterator();
		Iterator<String> iterator1 = author.getLinkBooks().iterator();
		
		while(iterator.hasNext() && iterator1.hasNext()){
			String bookName = iterator.next();
			String linkBook = iterator1.next();
			authorInfo = authorInfo + "<a href='" + linkBook + "' target='_blank'/>" + bookName.replace("@en", "") + "<br/>";
		}
		authorInfo = authorInfo	+ "</td>"
							+ "</tr>" 
							+ "<tr>"
							+ 	"<td>Same as</td>"
							+ 	"<td>"
							+ "<a href='" + author.getSameAs() + "' target='_blank'>" + author.getSameAs() + "</a>"
							+	"</td>"
							+ "</tr>"
							+ "<tr>"
							+ 	"<td>See also</td>"
							+ 	"<td>"
							+ "<a href='" + author.getSeeAlso() + "' target='_blank'>" + author.getSeeAlso() + "</a>"
							+	"</td>"
							+ "</tr>"
							+ "</tr>"
							+ "</table>";
		
		out.println(authorInfo);
	}
	
%>