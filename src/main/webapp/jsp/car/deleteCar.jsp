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

    <title>Delete Car</title>
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
            <tr class="table-ro">
                <th scope="col"> #</th>
                <th scope="col"> ID Number</th>
                <th scope="col"> Model</th>
                <th scope="col"> Brand</th>
                <th scope="col"> Date of Production</th>
                <th scope="col"> Next Checkup Date</th>
                <th scope="col"> Client</th>
                <th scope="col"> Delete</th>
            </tr>

            <c:forEach items="${cars}" var="car">

                <tr class="table-ro">
                    <th><c:out value="${id}"/></th>
                    <td>${car.idNumber}</td>
                    <td>${car.model}</td>
                    <td>${car.brand}</td>
                    <td>${car.dateProduction}</td>
                    <td>${car.nextCheckupDate}</td>
                    <td>${car.client.firstName} ${car.client.lastName}</td>
                    <td><a href="#" class="btn btn-primary btn-sm active" role="button" data-toggle="modal"
                           data-target="#myModal" id="deleteBtn">delete</a></td>

                </tr>
                <c:set var="id" value="${id + 1}" scope="request"/>
            </c:forEach>
        </table>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Delete Car</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="deleteModalBody">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                    <button type="button" id="yesButton" data-dismiss="modal" class="btn btn-secondary">Yes</button>
                </div>
            </div>
        </div>
    </div>
</main>


</body>
</html>
