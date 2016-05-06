<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Profile</title>
</head>
<body>


	<form:form method="POST" modelAttribute="employee">
		<h2>Employee Information</h2>
		<table>
			<tr>
				<td><label for="firstName">First Name: </label></td>
				<td><form:input path="firstName" id="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="middleName">Middle Name: </label></td>
				<td><form:input path="middleName" id="middleName" /></td>
				<td><form:errors path="middleName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="lastName">Last Name: </label></td>
				<td><form:input path="lastName" id="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="email">E-mail: </label></td>
				<td><form:input path="email" id="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="id">Employee Number: </label></td>
				<td><form:input path="id" id="id" /></td>
				<td><form:errors path="id" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="position">Position: </label></td>
				<td><form:input path="position" id="position" /></td>
				<td><form:errors path="position" cssClass="error" /></td>
			</tr>

		</table>

		<br />
		<h2>Contact Information</h2>
		<table>
			<tr>
				<td><label for="birthDate">Birthdate: </label></td>
				<td><form:input path="birthDate" id="birthDate" /></td>
				<td><form:errors path="birthDate" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="gender">Gender: </label></td>
				<td><form:input path="gender" id="gender" /></td>
				<td><form:errors path="gender" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="phoneNumber">Phone Number: </label></td>
				<td><form:input path="phoneNumber" id="phoneNumber" /></td>
				<td><form:errors path="phoneNumber" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="address">Address: </label></td>
				<td><form:input path="address" id="address" /></td>
				<td><form:errors path="address" cssClass="error" /></td>
			</tr>

		</table>
	</form:form>

	<br />
	<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>