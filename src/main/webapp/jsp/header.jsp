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

    <title>Header</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/css.css">
</head>
<body>
<header>

    <nav class="navbar navbar-dark bg-primary navbar-expand-lg" id="mainNavbar">
        <a class="navbar-brand" href="main.html"><img src="/img/samochod_71431-58.jpg" width="30" height="30"
                                                      class="d-inline-block mr-1 align-bottom" alt=""> Car Workshop </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="main.html">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainmenu">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link" href="main.html"> Start</a>
                </li>

                <li class="nav-item dropdown" id="order">
                    <div class="nav-link dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true"
                         aria-expanded="false" id="submenuOrders"> Orders
                    </div>
                    <div class="dropdown-menu">
                        <a href="/createorder" class="dropdown-item"> Create Order </a>
                        <div class="dropdown-divider"></div>
                        <a href="/showallorders" class="dropdown-item"> Show All Orders</a>
                        <a href="/updateorder" class="dropdown-item"> Edit Order </a>
                        <a href="/deleteorder" class="dropdown-item"> Delete Order </a>

                    </div>
                </li>

                <li class="nav-item dropdown" id="client">
                    <div class="nav-link dropdown-toggle" data-toggle="dropdown" aria-expanded="false"
                         aria-haspopup="true" id="submenuClients" role="button"> Clients
                    </div>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/createclient"> Create Client </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/showallclients"> Show All Clients </a>
                        <a class="dropdown-item" href="/updateclient"> Edit Client</a>
                        <a class="dropdown-item" href="/deleteclient"> Delete Client </a>

                    </div>
                </li>

                <li class="nav-item dropdown" id="cars">
                    <div class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"
                         aria-haspopup="true" id="submenuCar"> Cars
                    </div>
                    <div class="dropdown-menu" aria-labelledby="submenuCar">
                        <a class="dropdown-item" href="/createcar"> Create Car </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/showallcars"> Show All Cars </a>
                        <a class="dropdown-item" href="/updatecar"> Edytuj Samochód </a>
                        <a class="dropdown-item" href="/deletecar"> Usuń Samochód </a>

                    </div>
                </li>
                <li class="nav-item dropdown" id="employee">
                    <div class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"
                         aria-haspopup="true" id="submenuPracownik"> Employees
                    </div>
                    <div class="dropdown-menu" aria-labelledby="submenuPracownik">
                        <a href="/createemployee" class="dropdown-item"> Create Employee </a>
                        <div class="dropdown-divider"></div>
                        <a href="/showallemployees" class="dropdown-item mr-1"> Show All Employees </a>
                        <a href="/updateemployee" class="dropdown-item"> Edit Employee</a>
                        <a href="/deleteemployee" class="dropdown-item"> Delete Employee </a>

                    </div>
                </li>

            </ul>

            <form class="form-inline" id="searching">

                <input class="form-control" type="search" id="findBar" placeholder="Search" aria-label="Search">
                <button class="btn btn-light" id="findBtn" type="submit">Find</button>

            </form>
        </div>


    </nav>
</header>


<script src="/js/jquery-3.2.1.slim.min.js"></script>
<script src="/js/popper.min.js"></script>

<script src="/js/bootstrap.js"></script>
<script src="/js/bootstrap.bundle.js"></script>
<script src="/js/jquery.js"></script>
<script src="/js/my.js"></script>
</body>
</html>