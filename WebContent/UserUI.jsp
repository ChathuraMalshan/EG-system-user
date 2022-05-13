<%@ page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/uservalidation.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>User Service</h1>
				<form id="formUser" name="formUser" method="post" action="UserUI.jsp">  
					User Name:  
 	 				<input id="UserName" name="UserName" type="text"  class="form-control form-control-sm">
					<br>Address:   
  					<input id="UserAddress" name="UserAddress" type="text" class="form-control form-control-sm">   
  					<br>Mobile:   
  					<input id="UserMobile" name="UserMobile" type="text"  class="form-control form-control-sm">
  					<br>Email:   
  					<input id="UserEmail" name="UserEmail" type="text"  class="form-control form-control-sm">
  					<br>Account Number:   
  					<input id="UserAccNo" name="UserAccNo" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divUserGrid">
					<%
					    User UserObj = new User();
						out.print(UserObj.readUser());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>