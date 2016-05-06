<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="headitems.jsp" />

<title>Employee Home</title>
</head>
<body>

	<jsp:include page="nav.jsp" />

	<div class="container">

		<!-- 		<div class="page-header">
			<h4>Employee Profile</h4>
		</div> -->

		<h1>Employee Profile</h1>

		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left" style="padding-top: 7.5px;">Basic Information</h3>
					<a href="<c:url value="/edit-${employee.id}-employee" />" class="btn btn-default btn-sm pull-right">Update Information</a>
				</div>
				<table class="table">
					<tr>
						<td class="col-md-2">Name:</td>
						<td>${employee.firstName} ${employee.middleName} ${employee.lastName}</td>
					</tr>

					<tr>
						<td class="col-md-2">E-mail: </td>
						<td>${employee.email}</td>
					</tr>

					<tr>
						<td class="col-md-2">Birthdate: </td>
						<td><fmt:formatDate type="date" value='${primBday}' pattern='MMMM dd, yyyy'  /></td>
					</tr>
					
					<tr>
						<td class="col-md-2">Age: </td>
						<td>${age} year<c:if test="${age > 1}">s</c:if> old</td>
					</tr>
					
					<tr>
						<td class="col-md-2">Gender: </td>
						<td>
							<c:choose>
							<c:when test="${employee.gender == 'M'}">
								Male
							</c:when>
							<c:otherwise>
 								Female
  							</c:otherwise>
  							</c:choose>
  						</td>
					</tr>

					<tr>
						<td class="col-md-2">Employee Number: </td>
						<td>${employee.id}</td>
					</tr>

					<tr>
						<td class="col-md-2">Position: </td>
						<td>${employee.position}</td>
					</tr>
				</table>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left" style="padding-top: 7.5px;">Contact Information</h3>
					<a href="<c:url value="/edit-${employee.id}-employee" />" class="btn btn-default btn-sm pull-right">Update Information</a>
				</div>
				<table class="table">
					<tr>
						<td class="col-md-2">Phone Number:</td>
						<td>${employee.phoneNumber}</td>
					</tr>

					<tr>
						<td class="col-md-2">Address: </td>
						<td>${employee.address}</td>
					</tr>
				</table>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left" style="padding-top: 7.5px;">Leaves Information</h3>
					<a href="#" class="btn btn-default btn-sm pull-right">View Leaves</a>
				</div>
				<table class="table">
					<tr>
						<td class="col-md-2">Leaves Remaining:</td>
						<td>${employee.numLeaves}</td>
					</tr>
				</table>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left" style="padding-top: 7.5px;">Salary Information</h3>
					<a href="#" class="btn btn-default btn-sm pull-right">View Salary</a>
				</div>
				<table class="table">
					<tr>
						<td class="col-md-2">Current Salary:</td>
						<td>10</td>
					</tr>
				</table>
			</div>
		</div>

	</div>
	<!-- /container -->

</body>
</html>
