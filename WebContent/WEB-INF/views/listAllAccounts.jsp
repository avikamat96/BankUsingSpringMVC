<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<table class="table table-striped table-bordered">
		<tr>
			<th>AccountNumber</th>
			<th>Name</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Account Type</th>
			<th>Balance</th>
		</tr>
		<c:forEach var="account" items="${accountList}">
		<tr>
			<td><c:out value="${account.getAccountNumber()}" /></td>
			<td><c:out value="${account.getUser().getName()}" /></td>
			<td><c:out value="${account.getUser().getAge()}" /></td>
			<td><c:out value="${account.getUser().getGender()}" /></td>
			<td><c:out value="${account.getAccountType()}" /></td>
			<td><c:out value="${account.getAccountBalance()}" /></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>