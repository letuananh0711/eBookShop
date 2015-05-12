<%@page import="ebookshop.beans.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page language="java" contentType="text/html"%>
<% String base = (String)application.getAttribute("base"); %>
<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager"/>

<div class="leftmenu">
	<div class="box">
<%
	String userName = (String)session.getAttribute("userName");
	if(userName != null){
%>
		<p>Hello <%=userName%>!</p>
		<p><a href="<%=base%>?action=logout">Logout</a></p>
<%
	}
	else
	{
%>		
		<a href="<%=base%>?action=login">Login</a>
<%
	}
%>
	</div>
	<hr>
	<div class="box">
		<div class="title">Categories</div>
<%
	ArrayList<Category> categories = dataManager.getCategories();
	Iterator<Category> iterator = categories.iterator();
	while(iterator.hasNext()){
		Category category = (Category)iterator.next();
		int id = category.getIdGenreLivre();
		out.println("<p><a href=" + base + "?action=selectCatalog&id="
	        	+ id + ">" + category.getNomGenreLivre() + "</a></p>");
	}
%>		
  	</div>
  	<hr> 
  	<div class="box">
    	<div class="title">Quick Search</div>
	    	<p>Book Title/Author:</p>
	    	<form style="border: 0px solid; padding: 0; margin: 0;">
		    	<input type="hidden" name="action" value="search"/>
		    	<input id="text" type="text" name="keyword" size="15"/>
		    	<input id="submit" type="submit" value="Search"/>
	    	</form>
  	</div>
  	<div class="box">
  		<div class="title">Advanced Search</div>
  			<p>Book Title:</p>
  			<form style="border: 0px solid; padding: 0; margin: 0;">
		    	<input type="hidden" name="action" value="searchAdvanced"/>
		    	<input id="text" type="text" name="keywordAdvanced" size="15"/>
		    	<input id="submit" type="submit" value="Search"/>
	    	</form>
  	</div>
	<hr>
	<div class="box">
		<div class="title">Supporter</div>
		<script type="text/javascript" src="http://cdn.dev.skype.com/uri/skype-uri.js"></script>
		<div id="SkypeButton_Dropdown_alk1012_1">
		<script type="text/javascript">
		    Skype.ui({
		      "name": "dropdown",
		      "element": "SkypeButton_Dropdown_alk1012_1",
		      "participants": ["alk1012"],
		      "imageSize": 24
		    });
	  	</script>
		</div>
	</div>
</div>