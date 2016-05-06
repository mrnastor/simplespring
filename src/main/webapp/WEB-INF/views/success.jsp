<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <jsp:include page="headitems.jsp" />
    <title>Result - Success</title>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="container">
        <c:choose>
            <c:when test="${edit}"><h1>Update Complete</h1></c:when>
            <c:when test="${deleted}"><h1>Deletion Complete</h1></c:when>
            <c:otherwise><h1>Registration Complete</h1></c:otherwise>
        </c:choose>
        
         <div class="panel panel-default">
<!--            <div class="panel-heading">
                <h3 class="panel-title">Success!</h3>
            </div>-->
             <div class="panel-body">
                ${success}
                <br />
                <br /> 
                <c:if test="${admin}">
                    <div>Go to <a href="<c:url value='/list' />">List of All Employees</a></div>
                </c:if>
                <c:if test="${!deleted}">
                    <div>
                        Go to <a href="<c:url value='/empinfo-${employee.id}' />">Employee Profile</a>
                    </div>
                </c:if>
                    
             </div>
         </div>
        
    </div>
	
</body>

</html>