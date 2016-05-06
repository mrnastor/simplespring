<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="headitems.jsp" />
    <title>Employee List</title>
</head>
 
 
<body>
    <jsp:include page="nav.jsp" />
    <div class="container">
        <h1>Employee Management</h1>
        <div class="panel panel-default">
            <div class="panel-heading clearfix">
                <h3 class="panel-title pull-left" id='employee-list-panel-title'>Employee List</h3>
                <a href="<c:url value='/new' />" class="btn btn-primary btn-sm pull-right">Add New Employee</a>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div>
                        <form action="search" class="form-inline">
                            <div class="col-lg-12">
                                <input type="text" name="searchText" class="form-control" value="${searchText}" placeholder="Type employee name or number" id="employee-search-input" />
                                <button type="submit" class="btn btn-default">Search Employees</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-lg-12" id="employee-search-result">
                        <c:if test="${empty employees}">
                            No employees found.
                        </c:if>
                        <c:if test="${not empty employees}">
                            <c:if test="${searchText != null}"> 
                                    Search results for "<c:out value="${searchText}"></c:out>".
                            </c:if> 
                    </div>
                </div>
                
                <table class="table table-bordered table-condensed table-hover table-striped" id="employee-table">
                    <thead>
                        <tr>
                            <th>Employee ID</th>
                            <th>Name</th>
                            <th>Department</th>
                            <th>Position</th>
                        </tr>
                    </thead>    
                    <tbody>
                        <c:forEach items="${employees}" var="employee">
                            <tr>
                                <td>${employee.id}</td>
                                <td><a href="<c:url value='/empinfo-${employee.id}' />">${employee.lastName}, ${employee.firstName} ${employee.middleName}</a></td>
                                <td>${employee.deptNumber.deptName}</td>                
                                <td>${employee.position}</td>
                            </tr>
                    
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            </div>
        </c:if> 
    </div>
    
</body>
</html>
