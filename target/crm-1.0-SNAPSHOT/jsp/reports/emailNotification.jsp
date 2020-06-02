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

    <title>Email Notification</title>

</head>
<body id="car">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>

<main id="test2">
    <form action="/emailnotification" method="post">
        <div class="row col-md-9">
            <table class="table table-striped table-bordered table-sm table-hover" id="tbb">
                <tr class="table-ro">
                    <th scope="col"> #</th>
                    <th scope="col"> ID</th>
                    <th scope="col">First name</th>
                    <th scope="col">Last name</th>
                    <th scope="col">Birthdate</th>
                    <th scope="col">ID Number</th>
                    <th scope="col">Brand</th>
                    <th scope="col">Model</th>
                    <th scope="col">Next Checkup Date</th>
                </tr>

                <c:forEach items="${cars}" var="car">
                    <tr class="table-ro" id="tabb">
                        <th id="test"><c:out value="${id}"/></th>
                        <td>${car.id}</td>
                        <td>${car.client.firstName}</td>
                        <td>${car.client.lastName}</td>
                        <td>${car.client.birthDate}</td>
                        <td>${car.idNumber}</td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.nextCheckupDate}</td>
                    </tr>
                </c:forEach>
            </table>

            <div class="form-row">
                <div class="form-group col-md-3">
                    <button id="emailBtn" class="btn btn-primary active" type="submit"> Submit </button>
                </div>
            </div>
            <div class="row col-md-9">
                <div class="form-group col-md-9">${empty message ? "" : message}</div>
            </div>
        </div>
    </form>
</main>
</body>
</html>
