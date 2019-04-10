<%@page import="com.epam.models.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%
	  if (session.getAttribute("isUserLoggedIn") == null) {
	    request.getRequestDispatcher("login.jsp").forward(request, response);
	  }
	%>
<jsp:include page="dashboardHeader.jsp" /> 
	<form class="form-vertical" role="form" action="updateaccount"
		method="post">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name</label>

			<div class="col-sm-10">
				<input type="text" class="form-control" id="name"
					value="${account.getUser().getName()}" placeholder="Name"
					name="updatedUserName" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="age" class="col-sm-2 control-label">Age</label>

			<div class="col-sm-10">
				<input type="number" class="form-control" id="age"
					value="${account.getUser().getAge()}" placeholder="Age"
					name="updatedUserAge" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="gender" class="col-sm-2 control-label">Gender</label>
			<div class="col-sm-10">
				<input type=text class="form-control" id="gender"
					value="${account.getUser().getGender()}" placeholder="Gender"
					readonly="readonly"><input type="hidden"
					value="${account.getUser().getGender()}" name="updatedUserGender">
			</div>
		</div>
		<div class="form-group">
			<label for="accountType" class="col-sm-2 control-label">Account Type</label>
			<div class="col-sm-10">
				<input type=text class="form-control" id="accountType"
					value="${account.getAccountType()}" readonly="readonly"> <input
					type="hidden" value="${account.getAccountType()}"
					name="updatedUserAccountType">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10">
				<input type="hidden" class="form-control" id="accountNumber"
					value="${account.getAccountNumber()}" disabled="disabled"> <input
					type="hidden" value= "${account.getAccountNumber()}"
					name="updatedUserAccountNumber">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</div>
	</form>
</body>
</html>