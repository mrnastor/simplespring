<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="headitems.jsp" />

<script>
	var empId = '${employee.id}';
	var postUrl = "<c:url value="/changepassword" />";
	var successUrl = "<c:url value="/home" />";
	var token = "${_csrf.token}";
	var curEmployee = "${employee}";
	var passFail = '${passwordfail}';
</script>

<title>Change Password</title>
</head>
<body>

	<jsp:include page="nav.jsp" />

	<div class="container">

		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading passtop">
					<h3 class="panel-title">Change Password</h3>
				</div>
				<form class="form-horizontal" id="passwordform">
					<div class="form-group">
						<label for="oldPassword"
							class="control-label col-xs-2 col-xs-offset-1">Old
							Password:</label>
						<div class="col-xs-4">
							<input path="oldPassword" id="oldPassword" type="password"
								class="form-control" placeholder="" />
						</div>
					</div>
					<div class="form-group">
						<label for="newPassword"
							class="control-label col-xs-2 col-xs-offset-1">New
							Password:</label>
						<div class="col-xs-4">
							<input path="newPassword" id="newPassword" type="password"
								class="form-control" placeholder="" />
						</div>
					</div>
					<div class="form-group">
						<label for="confirmPassword"
							class="control-label col-xs-2 col-xs-offset-1">Confirm
							Password:</label>
						<div class="col-xs-4">
							<input path="confirmPassword" id="confirmPassword"
								type="password" class="form-control" placeholder="" />
						</div>
					</div>
					<div class="row passbottom">
						<div class="col-xs-4 pull-left">
							<div class="col-xs-2 col-xs-offset-8">
								<button type="submit" class="btn btn-primary" id="savepass">Save</button>
							</div>
							<div class="col-xs-2">
								<a href="<c:url value="/home" />" role="button"
									class="btn btn-primary">Cancel</a>
							</div>
						</div>
					</div>

				</form>


			</div>
		</div>

	</div>
	<!-- /container -->

	<div id="user_dialog" style="display: none;">
		<p></p>
	</div>
</body>
</html>