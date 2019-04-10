<%@page import="com.epam.models.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<table class="table table-bordered">
		<tr>
			<th>Name</th>
			<td>${account.getUser().getName()}</td>
		</tr>
		<tr>
			<th>Age</th>
			<td>${account.getUser().getAge()}</td>
		</tr>
		<tr>
			<th>Gender</th>
			<td>${account.getUser().getGender()}</td>
		</tr>
		<tr>
			<th>Account Type</th>
			<td>${account.getAccountType()}</td>
		</tr>
		<tr>
			<th>Account Number</th>
			<td>${account.getAccountNumber()}</td>
		</tr>
		<tr>
			<th>Account Balance</th>
			<td>${account.getAccountBalance()}</td>
		</tr>
	</table>
	<form class="form-vertical" role="form" action="dashboard" >
    <div class="form-group" align="center">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">OK</button>
			</div>
		</div>

	</form>
</body>
</html>