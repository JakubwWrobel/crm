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

    <title>Show All Cars</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body id="car">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>

<main>
    <c:set var="id" scope="request" value="${1}"/>
    <div class="row col-md-9">
        <table class="table table-striped table-bordered table-sm table-hover">
            <tr class="table-row">
                <th scope="col"> #</th>
                <th scope="col"> ID Number </th>
                <th scope="col"> Model </th>
                <th scope="col"> Brand </th>
                <th scope="col"> Date of Production </th>
                <th scope="col"> Next Checkup Date </th>
                <th scope="col"> Client </th>
            </tr>

            <c:forEach items="${cars}" var="car">

                <tr class="table-row" data-href="\showallcars?id=${car.id}">
                    <th><c:out value="${id}"/></th>
                    <td>${car.idNumber}</td>
                    <td>${car.model}</td>
                    <td>${car.brand}</td>
                    <td>${car.dateProduction}</td>
                    <td>${car.nextCheckupDate}</td>
                    <td>${car.client.firstName} ${car.client.lastName}</td>
                </tr>
                <c:set var="id" value="${id + 1}" scope="request"/>
            </c:forEach>
        </table>
    </div>
</main>

</body>
</html>
