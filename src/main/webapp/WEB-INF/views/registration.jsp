<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
 
<head>
    <jsp:include page="headitems.jsp" />
    
    <c:choose>
        <c:when test="${edit}"><title>Update Employee</title></c:when>
        <c:otherwise><title>Register Employee</title></c:otherwise>
    </c:choose>
 
</head>
 
<body>
    <jsp:include page="nav.jsp" />
    <div class="container">
        <c:choose>
            <c:when test="${edit}"><h1>Update Employee Information</h1></c:when>
            <c:otherwise><h1>Register Employee</h1></c:otherwise>
        </c:choose>
            
        <form:form method="POST" modelAttribute="employee">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Basic Information</h3>
            </div>
            <div class="panel-body">
                 <div class="form-group row">
                    <c:if test="${edit}">
                        <form:label path="id" cssClass="col-sm-2 form-control-label">Employee Number</form:label> 
                        <div class="col-sm-10">
                            <c:out value="${employee.id}"/>
                        </div>
                    </c:if>
                </div>
                <div class="form-group row">
                   <form:label path="firstName" cssClass="col-sm-2 form-control-label">First Name </form:label>
                   <div class="col-sm-6"><form:input path="firstName" id="firstName" cssClass="form-control"/></div>
                    <div><form:errors path="firstName" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                   <form:label path="middleName" cssClass="col-sm-2 form-control-label">Middle Name </form:label>
                    <div class="col-sm-6"><form:input path="middleName" id="middleName" cssClass="form-control"/></div>
                    <div><form:errors path="middleName" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                   <form:label path="lastName" cssClass="col-sm-2 form-control-label">Last Name </form:label>
                    <div class="col-sm-6"><form:input path="lastName" id="lastName" cssClass="form-control"/></div>
                    <div><form:errors path="lastName" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                   <form:label path="email" cssClass="col-sm-2 form-control-label">E-mail </form:label>
                    <div class="col-sm-6"><form:input path="email" id="email" cssClass="form-control"/></div>
                    <div><form:errors path="email" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                   <form:label path="deptNumber.deptNumber" cssClass="col-sm-2 form-control-label">Department </form:label>
                    <div class="col-sm-6">
                        <form:select path="deptNumber.deptNumber" items="${departmentList}" itemValue="deptNumber" itemLabel="deptName" cssClass="form-control"/>
                    </div>
                    <div><form:errors path="deptNumber.deptName" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                   <form:label path="position" cssClass="col-sm-2 form-control-label">Position </form:label>
                    <div class="col-sm-6"><form:input path="position" id="position" cssClass="form-control"/></div>
                    <div><form:errors path="position" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                   <form:label path="password" cssClass="col-sm-2 form-control-label">Password </form:label>
                    <div class="col-sm-6"><form:input path="password" id="password" cssClass="form-control"/></div>
                    <div><form:errors path="password" cssClass="error"/></div>
                </div>
            </div>
                
            <div class="panel-heading">
                <h3 class="panel-title">Contact Information</h3>
            </div>
<!--            <h3 class="panel-title">Contact Information</h3>-->
            <div class="panel-body">
                <div class="form-group row" cssClass="col-sm-2 form-control-label">
                    <form:label path="birthDate" cssClass="col-sm-2 form-control-label">Birthdate </form:label>
                    <div class="col-sm-6">
                        <input type="hidden" name="birthDate" value="to be ignored" /> 
                        Year:
                        <form:select path="birthYear">
                            <form:options items="${yearList}"/>
                        </form:select>
                        Month:
                        <form:select path="birthMonth">
                            <form:options items="${monthList}"/>
                        </form:select>

                        Day: 
                        <form:select path="birthDay">
                            <form:options items="${dayOfMonthList}"/>
                        </form:select>
                    </div>
                    <div><form:errors path="birthDate" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                    <form:label path="gender" cssClass="col-sm-2 form-control-label">Gender </form:label>
                    <div class="col-sm-6">
                        <div class="radio-inline">
                            <label>
                                <form:radiobutton path="gender" value="M" /> Male
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label>
                                <form:radiobutton path="gender" value="F" /> Female
                            </label>
                        </div>
                        
                    </div>
                    <div><form:errors path="gender" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                    <form:label path="phoneNumber" cssClass="col-sm-2 form-control-label">Phone Number </form:label> 
                    <div class="col-sm-6"><form:input path="phoneNumber" id="phoneNumber" cssClass="form-control"/></div>
                    <div><form:errors path="phoneNumber" cssClass="error"/></div>
                </div>
                <div class="form-group row">
                    <form:label path="address" cssClass="col-sm-2 form-control-label">Address </form:label>
                    <div class="col-sm-6"><form:input path="address" id="address" cssClass="form-control"/></div>
                    <div><form:errors path="address" cssClass="error"/></div>
                </div>
            </div>
               
                
                <div class="form-group row">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class=" btn-toolbar">
                            <c:choose>
                                <c:when test="${edit}">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary">Register</button>
                                </c:otherwise>
                            </c:choose>
                            <button type="button" value="Cancel" name="cancel" onclick="history.go(-1);" class="btn btn-primary">Cancel</button>
                        </div>
                    </div>
                </div>
        </div>
        </form:form>
    </div>
    
</body>

</html>