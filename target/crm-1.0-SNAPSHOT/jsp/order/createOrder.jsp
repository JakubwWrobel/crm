<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: kuba
  Date: 23.03.2020
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <%--    SCRIPT TO SEARCH IN SELECT!--%>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>

    <title>Create Order</title>
</head>
<body id="order">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>
<main>
    <form action="${pageContext.request.contextPath}/createorder" method="post" class="form">
        <div class="form-row">

            <div class="form-group col-md-3">
                <label for="dateReceived"> Provide Received Date </label>
                <input type="date" class="form-control " id="dateReceived" name="dateReceived" required>
            </div>
            <div class="form-group col-md-3 ">
                <label for="problemDes"> Describe the Problem </label>
                <input type="text" class="form-control " id="problemDes" name="problemDes" required>
            </div>

            <div class="form-group col-md-3">
                <label for="car"> Choose a Car </label>
                <select class="form-control" id="car" name="car" data-live-search="true" required>
                    <c:forEach items="${cars}" var="car">
                        <option value="${car.id}" ><c:out value="${car.idNumber}"></c:out></option>
                    </c:forEach>
                </select>
                <span class="help-inline">Start typing to find a car</span>
            </div>

        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="employeeAssigned"> Assign employee </label>
                <select class="form-control" id="employeeAssigned" name="employeeAssigned" data-live-search="true" required>
                    <c:forEach items="${employees}" var="employee">
                        <option value="${employee.id}"><c:out value="${employee.firstName} ${employee.lastName}"></c:out></option>
                    </c:forEach>
                </select>
                <span class="help-inline">Start typing to find an employee</span>
            </div>
            <div class="form-group col-md-3">
                <label for="plannedDateStartRepair"> Provide Planned Repair Start Date </label>
                <input type="date" class="form-control" id="plannedDateStartRepair" name="plannedDateStartRepair"
                       required>
            </div>
            <div class="form-group col-md-3">
                <label for="status"> Provide Status </label>
                <select class="form-control" id="status" name="status" data-live-search="true">
                        <option value="RECEIVED"> Received </option>
                        <option value="INPROGRESS"> In Progress </option>
                        <option value="COST_APPROVED"> Cost Approved </option>
                        <option value="READY_TO_PICK"> Ready to Pick </option>
                        <option value="RESIGNED"> Client Resigned  </option>
                        <option value="DONE"> Done </option>

                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">
                <button class="btn btn-primary" type="submit"> Submit form</button>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">${empty message ? "" : message}</div>
        </div>
    </form>
</main>
</body>
</html>