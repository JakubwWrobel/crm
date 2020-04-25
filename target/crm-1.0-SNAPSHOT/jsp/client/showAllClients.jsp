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

    <title>Show All Clients</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body id="client">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>

<main>
    <c:set var="id" scope="request" value="${1}"/>
    <div class="row col-md-9">
        <table class="table table-striped table-bordered table-sm table-hover">
            <tr class="table-row">
                <th scope="col"> #</th>
                <th scope="col"> ID </th>
                <th scope="col"> First Name </th>
                <th scope="col"> Last Name </th>
                <th scope="col"> Birth Date </th>
            </tr>

            <c:forEach items="${clients}" var="client">

                <tr class="table-row" data-href="\showallclients?id=${client.id}">
                    <th><c:out value="${id}"/></th>
                    <td>${client.id}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.birthDate}</td>
                </tr>
                <c:set var="id" value="${id + 1}" scope="request"/>
            </c:forEach>
        </table>
    </div>
</main>

</body>
</html>
