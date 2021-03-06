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

    <title>Create Client</title>
</head>
<body id="client">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>
<main>
    <form action="${pageContext.request.contextPath}/createclient" method="post" class="form">
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="firstName"> Provide First Name</label>
                <input type="text" class="form-control " id="firstName" name="firstName" required>
            </div>
            <div class="form-group col-md-3 ">
                <label for="lastName"> Provide Last Name </label>
                <input type="text" class="form-control " id="lastName" name="lastName" required>
            </div>
            <div class="form-group col-md-3">
                <label for="birthDate"> Provide Birth Date</label>
                <input type="date" class="form-control " id="birthDate" name="birthDate" required>
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