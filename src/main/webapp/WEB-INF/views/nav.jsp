<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/" />">Company Name
				HRIS</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li <c:if test="${home}">class="active"</c:if>><a
					href="<c:url value='/home' />">Home</a></li>
				<li><a href="#">Apply Leaves</a></li>
				<li><a href="#">Timesheets</a></li>
				<li><a href="#">View Payslips</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${admin}">
					<li><a href="<c:url value="/list" />">Manage Employee</a></li>
				</c:if>
				<c:if test="${leaveapprover}">
					<li><a href="#">Approve
							Leaves</a></li>
				</c:if>
				
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">${username}<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/edit-${uid}-employee" />">Edit Profile</a></li>
						<li><a href="<c:url value="/changepassword" />">Change Password</a></li>
						<li><a href="<c:url value="/logout" />">Logout</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>