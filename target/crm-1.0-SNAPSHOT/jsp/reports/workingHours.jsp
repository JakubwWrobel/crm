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

    <title>Working Hours Report</title>

</head>
<body id="car">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>

<main>
    <form action="${pageContext.request.contextPath}/workinghours" method="post" class="form">


        <h4 class="form-group col-md-9">Work Time Reporting</h4>


        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="dateFrom"> Start Date </label>
                <input type="date" class="form-control " id="dateFrom" name="dateFrom" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <button class="btn btn-primary" type="submit"> Submit form</button>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">${empty message ? "" : message}</div>
        </div>
    </form>
    <div class="row col-md-9" id="whlist">
        <c:set var="id" value="${1}"/>
        <table class="table col-9-md table-striped table-bordered table-sm table-hover">
            <tr class="table-ro">
                <th scope="col"> #</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Hourly Cost</th>
                <th scope="col">Work Hours</th>
                <th scope="col">Total Cost</th>
            </tr>

            <c:forEach items="${workingHoursModelList}" var="whlist">

                <tr class="table-ro">
                    <th><c:out value="${id}"/></th>
                    <td>${whlist.firstName}</td>
                    <td>${whlist.lastName}</td>
                    <td>${whlist.hourlyCost}</td>
                    <td>${whlist.workHours}</td>
                    <td>${whlist.totalCost}</td>

                </tr>
                <c:set var="id" value="${id + 1}" />
            </c:forEach>
        </table>
    </div>

</main>

</body>
</html>
