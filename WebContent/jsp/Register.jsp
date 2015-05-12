<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eBookShop/css/ebookshop.css" type="text/css"/>
<script src="/eBookShop/js/ebookshop.js" type="text/javascript"></script>
<title>Register Page</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="TopMenu.jsp" flush="true"/>
	<div class="container">
		<jsp:include page="LeftMenu.jsp" flush="true"/>
			<div class="content">
			<form>
			<input type="hidden" name="action" value="registerCustomer" />
			<h1>Register</h1>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td colspan="2"><h3>Personal information</h3></td>
								</tr>
								<tr>
									<td>First name</td>
									<td><input type="text" name="firstName" value="" required="required"/></td>
								</tr>
								<tr>
									<td>Last name</td>
									<td><input type="text" name="lastName" value="" required="required"/></td>
								</tr>
								<tr>
									<td>Gender</td>
									<td>
										<select name="gender">
											<option value="1">Male</option>
											<option value="0">Female</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>DOB</td>
									<td><input type="date" name="dob" value="" /></td>
								</tr>
								<tr>
									<td>Email</td>
									<td><input type="email" name="email" value="" /></td>
								</tr>
								<tr>
									<td>Phone</td>
									<td><input type="text" name="phone" value=""/></td>
								</tr>
								<tr>
									<td>Address</td>
									<td><input type="text" name="address" value="" /></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td colspan="2"><h3>Account information</h3></td>
								</tr>
								<tr>
									<td>User name</td>
									<td>
										<input type="text" id="userName" name="userName" value="" required="required" onblur="isUserNameExist()"/>
										<div id="userNameCheck"></div>
									</td>
								</tr>
								<tr>
									<td>Password</td>
									<td><input type="password" id="passwd" name="passwd" value="" required="required"/></td>
								</tr>
								<tr>
									<td>Confirm Password</td>
									<td>
										<input type="password" id="confirmPasswd" name="confirmPasswd" value="" required="required" onblur="checkConfirmPassword()"/>
										<div id="confirmPasswordCheck"></div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<input type="submit" value="Register" />
			</form>	
			</div>
		</div>
	</div>
</body>
</html>