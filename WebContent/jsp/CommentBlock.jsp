
<%
	String commentBlock  = "<table>"
				+ "<tr>"
				+ "<td>"
				+ "<input type='text' name='reviewer' id='reviewer' placeholder='Name' />"
				+ "</td>"
				+ "<td>"
				+ "<input type='text' name='emailReviewer' id='emailReviewer' placeholder='Email' onblur='isEmail()' />"
				+ "<div id='checkEmail'></div>"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td colspan='2'>"
				+ "<textarea style='resize: none;' rows='8' cols='60' name='review' id='review' placeholder='Enter your review here...'></textarea>"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>"
				+ "<input type='text' name='point' id='point' placeholder='Point' onblur='checkPoint()' />"
				+ "<div id='checkPoint'></div>"
				+ "</td>"
				+ "<td>"
				+ "<input type='button' value='Comment' onclick='postReview()'/>"
				+ "</td>"
				+ "</tr>"
				+ "</table>";
				
		out.println(commentBlock);

%>	