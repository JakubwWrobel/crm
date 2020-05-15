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

    <title>Create Employee</title>
</head>
<body id="employee">
<header>
    <jsp:include page="/jsp/header.jsp"></jsp:include>
</header>
<main>
    <form action="${pageContext.request.contextPath}/createemployee" method="post" class="form">
        <div class="form-row">

            <div class="form-group col-md-3">
                <label for="name"> Podaj Imię </label>
                <input type="text" class="form-control " id="name" name="name" required>
            </div>
            <div class="form-group col-md-3 ">
                <label for="surname"> Podaj Nazwisko </label>
                <input type="text" class="form-control " id="surname" name="surname" required>
            </div>
            <div class="form-group col-md-3">
                <label for="address"> Podaj Adres </label>
                <input type="text" class="form-control " id="address" name="address" required>

            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6 offset-10">
                <label for="note"> Dodatkowe Informacje </label>
                <input type="text" class="form-control" id="note" name="note">
            </div>
            <div class="form-group col-md-3">
                <label for="phone"> Numer Telefonu </label>
                <input type="number" class="form-control" id="phone" min="0" name="phone" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="hourly"> Podaj Stawkę Godzinową </label>
                <input type="number" class="form-control"  id="hourly" name="hourly" required>
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