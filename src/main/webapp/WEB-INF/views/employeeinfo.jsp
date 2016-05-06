<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="headitems.jsp" />

<title>Employee Information</title>
</head>
<body>

	<jsp:include page="nav.jsp" />

	<div class="container">

		<div class="row">
                    <h1>Employee Information</h1>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Basic Information</h3>
				</div>
				<table class="table">
					<tr>
						<td class="col-md-2">First Name:</td>
						<td>${employee.firstName}</td>
					</tr>

					<tr>
						<td class="col-md-2">Middle Name:</td>
						<td>${employee.middleName}</td>
					</tr>

					<tr>
						<td class="col-md-2">Last Name:</td>
						<td>${employee.lastName}</td>
					</tr>

					<tr>
						<td class="col-md-2">E-mail:</td>
						<td>${employee.email}</td>
					</tr>

					<tr>
						<td class="col-md-2">Employee Number:</td>
						<td>${employee.id}</td>
					</tr>

					<tr>
						<td class="col-md-2">Position:</td>
						<td>${employee.position}</td>
					</tr>
				</table>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Contact Information</h3>
				</div>
				<table class="table">
					<tr>
						<td class="col-md-2">Birthdate:</td>
						<td><fmt:formatDate type="date" value='${primBday}'
								pattern='MMMM dd, yyyy' /></td>
					</tr>

					<tr>
						<td class="col-md-2">Gender:</td>
						<td><c:choose>
								<c:when test="${employee.gender == 'M'}">
								Male
							</c:when>
								<c:otherwise>
 								Female
  							</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<td class="col-md-2">Phone Number:</td>
						<td>${employee.phoneNumber}</td>
					</tr>

					<tr>
						<td class="col-md-2">Address:</td>
						<td>${employee.address}</td>
					</tr>
				</table>
			</div>
			<div class="text-center bottom_margin">
                            <div class=" btn-toolbar">
                                <a href="<c:url value="/edit-${employee.id}-employee" />"
					class="btn btn-primary text-center">Edit Employee
					Profile</a>
				<c:if test="${self eq false}">
					<a href="<c:url value="/delete-${employee.id}-employee" />"
						class="btn btn-primary text-center">Delete Employee
						Profile</a>
				</c:if>
                            </div>
				
			</div>

		</div>

	</div>
	<!-- /container -->

</body>
</html>
