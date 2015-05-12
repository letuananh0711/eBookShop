// JavaScript Document
var xmlhttp1;
var xmlhttp2;
var xmlhttp3;
var xmlhttp4;
var xmlhttp5;
var xmlhttp6;
var xmlhttp7;
var xmlhttp8;
var xmlhttp9;

function createXMLHttpRequest()
{
	var xmlhttp;	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

function isNumber(){
	
	var number = document.getElementById("quantity").value;
	
	if(isNaN(number)){
		document.getElementById("quantity").value = 1;
		alert("Please fill out quantity filed with a number!");
	}
	else{
		if(/^\d+$/.test(number) == false){
			document.getElementById("quantity").value = 1;
			alert("Please fill out quantity filed with a integer number!");
		}
	}
}

function isUserNameExist(){
	var userName = document.getElementById("userName").value;
	
	xmlhttp1 = createXMLHttpRequest();
	
	xmlhttp1.onreadystatechange = function()
	{
		if (xmlhttp1.readyState == 4 && xmlhttp1.status == 200){
			document.getElementById("userNameCheck").innerHTML = xmlhttp1.responseText;
			document.getElementById("userNameCheck").style.border = "0px";
			document.getElementById("userNameCheck").style.color = "red";
		}
	};
	
	var serverURL = "/eBookShop/jsp/CheckUserName.jsp?userName="+ encodeURI(userName);
	xmlhttp1.open("GET", serverURL, true);
	xmlhttp1.send();
}

function checkConfirmPassword(){
	var passWd = document.getElementById("passwd").value;
	var confirmPassWd = document.getElementById("confirmPasswd").value;
	
	if(passWd == confirmPassWd){
		document.getElementById("confirmPasswordCheck").innerHTML = "Match!";
		document.getElementById("confirmPasswordCheck").style.color = "green";
	}
	else{
		document.getElementById("confirmPasswd").value = "";
		document.getElementById("confirmPasswordCheck").innerHTML = "Not match!";
		document.getElementById("confirmPasswordCheck").style.color = "red";
	}
	
}

function downloadRDFDocument(bName, _bName){
	var rdfURL = "http://localhost:8080/eBookShop/RDFDocument/" + _bName + ".rdf";
	
	xmlhttp2 = createXMLHttpRequest();
	
	xmlhttp2.onreadystatechange = function()
	{
		if (xmlhttp2.readyState == 4 && xmlhttp2.status == 200){
			if(xmlhttp2.responseText == 1){
				window.open(rdfURL, '','width=600,height=300');
			}
			else{
				alert(xmlhttp2.responseText);
			}
				
		}
	};
	
	var serverURL = "/eBookShop/jsp/CreateRDFDocument.jsp?bookName="+ encodeURI(bName);
	xmlhttp2.open("GET", serverURL, true);
	xmlhttp2.send();
}

function postReview(){
	
	var bookId = document.getElementById("bookId").value;
	var name = document.getElementById("reviewer").value;
	var email = document.getElementById("emailReviewer").value;
	var point = document.getElementById("point").value;
	var review = document.getElementById("review").value;
	
	if(name == "" || email == "" || point == "" || review == ""){
		alert("Please fill out all fields!");
	}
	else{
		xmlhttp3 = createXMLHttpRequest();
		
		xmlhttp3.onreadystatechange = function()
		{
			if (xmlhttp3.readyState == 4 && xmlhttp3.status == 200){
				if(xmlhttp3.responseText == 1){
					alert("Thank you for your review!");
					setTimeout("location.reload(true);", 1000);
				}else{
					alert("Please try again!");
				}
			}
		};
		
		var serverURL = "/eBookShop/jsp/PostingComment.jsp?bookId=" + encodeURI(bookId) + "&name=" 
						+ encodeURI(name) + "&email=" + encodeURI(email) + "&point=" + encodeURI(point)
						+ "&review=" + encodeURI(review);
		xmlhttp3.open("GET", serverURL, true);
		xmlhttp3.send();
	}
}

function checkPoint(){
	var point = document.getElementById("point").value;
	
	if(isNaN(point) || point < 0 || point > 10 || point == ""){
		document.getElementById("checkPoint").innerHTML = "Invalid!";
		document.getElementById("checkPoint").style.color = "red";
		document.getElementById("point").value = "";
	}
	else{
		document.getElementById("checkPoint").innerHTML = "Valid!";
		document.getElementById("checkPoint").style.color = "green";
		document.getElementById("point").value = Number(point).toFixed(1);
	}
} 

function isEmail(){
	var email = document.getElementById("emailReviewer").value;
	
	var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (filter.test(email)){
		document.getElementById("checkEmail").innerHTML = "Valid!";
		document.getElementById("checkEmail").style.color = "green";
	} 
	else{
		document.getElementById("checkEmail").innerHTML = "Invalid!";
		document.getElementById("checkEmail").style.color = "red";
		document.getElementById("emailReviewer").value = "";
	}
}

function addNewCategory(){
	var cName = document.getElementById("categoryName").value;
	var cDes = document.getElementById("categoryDescription").value;
	
	if(cName == ""){
		alert("Please fill out category name!");
	}
	else{
		xmlhttp4 = createXMLHttpRequest();
		
		xmlhttp4.onreadystatechange = function()
		{
			if (xmlhttp4.readyState == 4 && xmlhttp4.status == 200){
				if(xmlhttp4.responseText == 1){
					alert("New book category was added!");
					setTimeout("location.reload(true);", 1000);
				}else{
					alert("Please try again!");
				}
			}
		};
		
		var serverURL = "/eBookShop/jsp-admin/AddNewCategory.jsp?cName=" + encodeURI(cName) + "&cDes=" + encodeURI(cDes);
		xmlhttp4.open("GET", serverURL, true);
		xmlhttp4.send();
	}
}

function addNewBook(){
	
	var bName = document.getElementById("bName").value;
	var bAuthor = document.getElementById("bAuthor").value;
	var bPublisher = document.getElementById("bPublisher").value;
	var bQuantity = document.getElementById("bQuantity").value;
	var bPrice = document.getElementById("bPrice").value;
	var bImage = document.getElementById("bImage").value;
	var bDes = document.getElementById("bDes").value;
	var bISBN = document.getElementById("bISBN").value;
	var bCategory = document.getElementById("bCategory").value;
	
	if(bName == "" || bAuthor == "" || bPublisher == "" || bQuantity == "" || bPrice == "" || bImage == "" || bDes == "" || bISBN == "" || bCategory == ""){
		alert("Please fill out all the field!");
	}
	else{
		xmlhttp5 = createXMLHttpRequest();
		
		xmlhttp5.onreadystatechange = function()
		{
			if (xmlhttp5.readyState == 4 && xmlhttp5.status == 200){
				if(xmlhttp5.responseText == 1){
					alert("New book was added!");
					setTimeout("location.reload(true);", 1000);
				}else{
					alert("Please try again!");
				}
			}
		};
		
		var serverURL = "/eBookShop/jsp-admin/AddNewBook.jsp?bName="+encodeURI(bName)
		+ "&bAuthor=" + encodeURI(bAuthor)
		+ "&bPublisher=" + encodeURI(bPublisher)
		+ "&bQuantity=" + encodeURI(bQuantity)
		+ "&bPrice=" + encodeURI(bPrice)
		+ "&bImage=" + encodeURI(bImage)
		+ "&bDes=" + encodeURI(bDes)
		+ "&bISBN=" + encodeURI(bISBN)
		+ "&bCategory=" + encodeURI(bCategory);
		
		xmlhttp5.open("GET", serverURL, true);
		xmlhttp5.send();
	}
}

function getAuthorInfo(authorName){
	
	xmlhttp6 = createXMLHttpRequest();
	
	xmlhttp6.onreadystatechange = function()
	{
		if (xmlhttp6.readyState == 4 && xmlhttp6.status == 200){
			if(xmlhttp6.responseText != 0){
				document.getElementById("info").innerHTML = xmlhttp6.responseText;
			}
			else{
				alert("Cannot find any infomation about " + authorName);
			}
		}
	};
	
	var serverURL = "/eBookShop/jsp/GetAuthorInfo.jsp?aName="+encodeURI(authorName);
	
	xmlhttp6.open("GET", serverURL, true);
	xmlhttp6.send();
}


function getPublisherInfo(publisherName){
	
	xmlhttp7 = createXMLHttpRequest();
	
	xmlhttp7.onreadystatechange = function()
	{
		if (xmlhttp7.readyState == 4 && xmlhttp7.status == 200){
			if(xmlhttp7.responseText != 0){
				document.getElementById("info").innerHTML = xmlhttp7.responseText;
			}
			else{
				alert("Cannot find any infomation about " + publisherName);
			}
		}
	};
	
	var serverURL = "/eBookShop/jsp/GetPublisherInfo.jsp?pName="+encodeURI(publisherName);
	
	xmlhttp7.open("GET", serverURL, true);
	xmlhttp7.send();
}

function getExtraComments(){
	
	var site = document.getElementById("referenceComment").value;
	
	var isbn = document.getElementById("isbn").value;
	
	xmlhttp8 = createXMLHttpRequest();
	
	xmlhttp8.onreadystatechange = function()
	{
		if (xmlhttp8.readyState == 4 && xmlhttp8.status == 200){
			if(xmlhttp8.responseText != 0){
				document.getElementById("extraComment").innerHTML = xmlhttp8.responseText;
			}
			else{
				alert("Cannot find any extra comment for this book");
			}
		}
	};
	
	var serverURL = "/eBookShop/jsp/GetExtraComments.jsp?isbn="+encodeURI(isbn)+"&site="+encodeURI(site);
	
	xmlhttp8.open("GET", serverURL, true);
	xmlhttp8.send();
}

function getCommentBox(){
	
	xmlhttp9 = createXMLHttpRequest();
	
	xmlhttp9.onreadystatechange = function()
	{
		if(xmlhttp9.readyState == 4 && xmlhttp9.status == 200)
		{
			if(document.getElementById("comment_box").innerHTML == "")
			{
				document.getElementById("comment_box").innerHTML = xmlhttp9.responseText;
				document.getElementById("buttonComment").value="-";
			}
			else
			{
				document.getElementById("comment_box").innerHTML = "";
				document.getElementById("buttonComment").value = "+";
			}
		}
	};
	
	var serverURL = "/eBookShop/jsp/CommentBlock.jsp";
	
	xmlhttp9.open("GET", serverURL, true);
	xmlhttp9.send();
}

