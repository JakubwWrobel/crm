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


    <title>Create Car</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>

</head>
<body id="car">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>
<main>
    <form action="${pageContext.request.contextPath}/createcar" method="post" class="form">
        <div class="form-row">

            <div class="form-group col-md-3">
                <label for="model"> Provide Model</label>
                <input type="text" class="form-control " id="model" name="model" required>
            </div>
            <div class="form-group col-md-3 ">
                <label for="brand"> Provide Brand</label>
                <input type="text" class="form-control " id="brand" name="brand" required>
            </div>
            <div class="form-group col-md-3">
                <label for="date"> Provide Production Date </label>
                <input type="date" class="form-control " id="date" name="date" required>

            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="id"> Provide ID Number</label>
                <input type="text" class="form-control" id="id" name="id" required>
            </div>
           <div class="form-group col-md-3">
                <label for="date2"> Provide Next Checkup Date </label>
                <input type="date" class="form-control" id="date2" name="date2" >
            </div>
            <div class="form-group col-md-3">
                <label for="clientId"> Choose a Client </label>
                <select class="form-control" id="clientId" name="clientId" data-live-search="true" required>
                    <c:forEach items="${clients}" var="client">
                        <option value="${client.id}"><c:out value="${client.firstName} ${client.lastName}"></c:out></option>
                    </c:forEach>
                </select>
                <span class="help-inline">Start typing to find a client</span>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">
                <button class="btn btn-primary" type="submit"> Submit form </button>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">${empty message ? "" : message}</div>
        </div>
    </form>

</main>
</body>
</html>