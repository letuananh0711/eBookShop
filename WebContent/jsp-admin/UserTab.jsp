<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ebookshop.beans.Customer" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<jsp:useBean id="dataManager" scope="application" class="ebookshop.model.DataManager" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop-admin.css" type="text/css" />
<script src="/eBookShop/js/paging.js" type="text/javascript"></script>
<title>User Tab</title>
</head>
<body>
	<jsp:include page="AdminTopMenu.jsp"></jsp:include>
		<h2>User List</h2>
		<table id="box-table-a">
			<tr>
				<th>User ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Address</th>
				<th>User Name</th>
				<th>Password</th>
				<th>Creation Date</th>
				<th>Status</th>
				<th>Privilege</th>
				<th>Action</th>
			</tr>
			<%
				ArrayList<Customer> customerList = dataManager.getCustomers();
				Iterator<Customer> iterator = customerList.iterator();
				while(iterator.hasNext()){
					Customer customer = iterator.next();
			%>
			<tr>
				<td><%=customer.getIdMembre()%></td>
				<td><%=customer.getPrenomMembre()%></td>
				<td><%=customer.getNomMembre()%></td>
				<td><%=customer.getSexe()%></td>
				<td><%=customer.getDateNaissance()%></td>
				<td><%=customer.getEmailMembre()%></td>
				<td><%=customer.getTelephone()%></td>
				<td><%=customer.getAdresse()%></td>
				<td><%=customer.getUserName()%></td>
				<td><%=customer.getPasswd()%></td>
				<td><%=customer.getDateCreation()%></td>
				<td><%=customer.getEtat()%></td>
				<td><%=customer.getPrivilege()%></td>
				<td>
					<input type="button" value="Action" />
				</td>
			</tr>
			<%		
				}
			%>
			<tr>	
			</tr>
		</table>
		<div id="pageNavPosition"></div>
		<script type="text/javascript">
	        var pager = new Pager('box-table-a', 8); 
	        pager.init(); 
	        pager.showPageNav('pager', 'pageNavPosition'); 
	        pager.showPage(1);
	   </script>
</body>
</html>