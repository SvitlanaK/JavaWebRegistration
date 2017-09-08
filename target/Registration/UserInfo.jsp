 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display</title>
<style>
table#nat{
	width: 50%;
	background-color: #c48ec5;
}
</style>
</head>
<body>
<%  String firstName = request.getParameter("firstName");
           String lastName = request.getParameter("lastName");
           String login = request.getParameter("login");
           String password = request.getParameter("password");
           String email = request.getParameter("email");
           String gender = request.getParameter("gender");
          %>
          Hello, <%= firstName %>
<table id ="nat"> <br>
 Your information
<tr>
	<td>FIRST NAME</td>
	<td><%= firstName %></td>
</tr>
<tr>
	<td>LAST NAME</td>
	<td><%= lastName %></td>
</tr>
<tr>
	<td>LOG IN</td>
	<td><%= login %></td>
</tr>
<tr>
	<td>PASSWORD</td>
	<td><%= password %></td>
</tr>
<tr>
	<td>E-MAIL</td>
	<td><%= email %></td>
</tr>
<tr>
	<td>GENDER</td>
	<td><%= gender %></td>
</tr>
</table>
</body>
</html>