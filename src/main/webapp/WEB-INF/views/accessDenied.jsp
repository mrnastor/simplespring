<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="headitems.jsp" />

<title>Access Denied</title>

</head>
<body>

	<jsp:include page="nav.jsp" />

	<div class="container">
		<div class="alert alert-danger" role="alert">
			<span class="sr-only">Error:</span> Dear <strong>${user}</strong>,
			You are not authorized to access this page
		</div>
	</div>
</body>
</html>