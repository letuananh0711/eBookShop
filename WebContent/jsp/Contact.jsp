<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css" />
<title>Contact page</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="TopMenu.jsp" flush="true" />
		<div class="container">
			<jsp:include page="LeftMenu.jsp" flush="true"/>
			<div class="content">
				<h3>Contact Us</h3>
				<p>Fill out the form below to get in touch with us.</p>
				<table>
					<tr>
						<td>Your name *</td>
						<td><input type="text" required="required" placeholder="Mr / Mrs..." /></td>
					</tr>
					<tr>
						<td>Your email *</td>
						<td><input type="text" required="required" placeholder="ex: abc@gmail.com" /></td>
					</tr>
					<tr>
						<td>Your phone</td>
						<td><input type="text" placeholder="ex: +84 1283529242" /></td>
					</tr>
					<tr>
						<td>Your Message *</td>
						<td><textarea rows="8" cols="50" placeholder="Something you would like to say..." style="resize: none;" ></textarea> </td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="Send" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>