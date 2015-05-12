<%@page language="java" contentType="text/html"%>
<%
	String base = (String)application.getAttribute("base");
	String imageURL = (String)application.getAttribute("imageURL");
%>

<div class="header">
	<div class="logo"><p>AUF eBookShop</p></div>
	<div class="cart">
  		<a class="link2" href="<%=base%>?action=showCart">Show Cart<img src="<%=imageURL%>cart.gif" border="0"/></a>
	</div>
   	<ul class="menu">
   		<li><a href="/eBookShop/shop/">Home</a></li>
   		<li><a href="<%=base%>?action=about">About</a></li>
   		<li><a href="<%=base%>?action=contact">Contact</a></li>
   	</ul>
</div>
