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

    <title>Update Order</title>
</head>
<body id="order">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>
<main>
    <form action="${pageContext.request.contextPath}/updateorder" method="post" class="form">
        <div class="form-row">

            <div class="form-group col-md-3">
                <label for="dateReceived"> Provide Received Date </label>
                <input type="date" class="form-control " id="dateReceived" name="dateReceived"
                       value="${fn:escapeXml(order.dateReceived)}" required>
            </div>
            <div class="form-group col-md-3 ">
                <label for="problemDes"> Describe the Problem</label>
                <input type="text" class="form-control " id="problemDes" name="problemDes"
                       value="${fn:escapeXml(order.problemDes)}" required>
            </div>

            <div class="form-group col-md-3">
                <label for="car"> Choose a Car</label>
                <select class="form-control" id="car" name="car" data-live-search="true" required>
                    <c:forEach items="${cars}" var="car">
                        <option value="${car.id}">${car.idNumber}</option>
                    </c:forEach>
                </select>
                <span class="help-inline">Start typing to find a car</span>
            </div>

        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="employeeAssigned"> Assign employee </label>
                <select class="form-control" id="employeeAssigned" name="employeeAssigned" data-live-search="true"
                        required>
                    <c:forEach items="${employees}" var="employee">
                        <option value="${employee.id}"><c:out
                                value="${employee.firstName} ${employee.lastName}"></c:out></option>
                    </c:forEach>
                </select>
                <span class="help-inline">Start typing to find an employee</span>
            </div>
            <div class="form-group col-md-3">
                <label for="plannedDateStartRepair"> Provide Planned Repair Start Date </label>
                <input type="date" class="form-control" id="plannedDateStartRepair" name="plannedDateStartRepair"
                       value="${fn:escapeXml(order.plannedDateStartRepair)}" required>
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
            <div class="form-group col-md-3">
                <label for="dateStartRepair"> Provide Start Repair Date </label>
                <input type="date" id="dateStartRepair" class="form-control" name="dateStartRepair"
                       value="${fn:escapeXml(order.dateStartRepair)}">
            </div>

            <div class="form-group col-md-3">
                <label for="resolutionDes"> Provide Resolution </label>
                <input type="test" class="form-control" id="resolutionDes" name="resolutionDes"
                       value="${fn:escapeXml(order.resolutionDes)}">
            </div>

            <div class="form-group col-md-3">
                <label for="costOfItems"> Cost of Items </label>
                <input type="number" class="form-control" step="0.01" placeholder='0.00' id="costOfItems"
                       name="costOfItems"
                       value="${fn:escapeXml(order.costOfItems)}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-row col-md-3">
                <label for="workHours"> Hours Spent</label>
                <input class="form-control" id="workHours" name="workHours" step="0.01" type="number" value="${fn:escapeXml(order.workHours)}">
            </div>
        </div>
        <div class="form-row mt-4">
            <div class="form-group col-md-2">
                <button class="btn btn-primary" type="submit"> Submit form</button>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">${empty message ? "" : message}</div>
        </div>
    </form>
</main>
<script src="/js/my.js"></script>
</body>
</html>