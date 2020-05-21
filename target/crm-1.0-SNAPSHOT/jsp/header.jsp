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

    <nav class="navbar navbar-dark bg-primary navbar-expand-lg">
        <a class="navbar-brand" href="#"><img src="/img/samochod_71431-58.jpg" width="30" height="30"
                                              class="d-inline-block mr-1 align-bottom" alt=""> Warsztat</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainmenu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainmenu">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/"> Start</a>
                </li>

                <li class="nav-item dropdown" id="order">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="submenuOrders"> Zamówienia </a>
                    <div class="dropdown-menu">
                        <a href="/createorder" class="dropdown-item"> Dodaj Zamówienia </a>
                        <a href="/showallorders" class="dropdown-item"> Pokaż Wszystkie Zamówienia </a>
                        <a href="/updateorder" class="dropdown-item"> Edytuj Zamówienie </a>
                    </div>
                </li>

                <li class="nav-item dropdown" id="client">
                    <a class="nav-link dropdown-toggle"  data-toggle="dropdown" aria-expanded="false" aria-haspopup="true" href="#" id="submenuClients" role="button"> Klienci</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/createclient"> Dodaj Klienta </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/showallclients"> Pokaż Wszystkich Klientów </a>
                        <a class="dropdown-item" href="/updateclient"> Edytuj Klienta </a>
                        <a class="dropdown-item" href="/deleteclient"> Usuń Klienta </a>

                    </div>
                </li>

                <li class="nav-item dropdown" id="cars">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" aria-haspopup="true" id="submenuCar" href="#" > Samochody </a>
                    <div class="dropdown-menu" aria-labelledby="submenuCar">
                        <a class="dropdown-item" href="/createcar" > Dodaj Samochód</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/showallcars"> Pokaż wszystkie samochody </a>
                        <a class="dropdown-item" href="/updatecar"> Edytuj Samochód </a>
                        <a class="dropdown-item" href="/deletecar"> Usuń Samochód </a>

                    </div>

                </li>
                <li class="nav-item dropdown" id="employee">

                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"
                       aria-haspopup="true" id="submenuPracownik" href="#"> Pracownicy</a>

                    <div class="dropdown-menu" aria-labelledby="submenuPracownik">

                        <a href="/createemployee" class="dropdown-item" > Dodaj Pracownika </a>

                        <div class="dropdown-divider"></div>

                        <a href="/showallemployees" class="dropdown-item mr-1"> Wyświetl Pracowników </a>
                        <a href="/updateemployee" class="dropdown-item"> Edytuj Pracownika </a>
                        <a href="/deleteemployee" class="dropdown-item"> Usuń Pracownika </a>

                    </div>
                </li>

            </ul>

            <form class="form-inline" id="searching">

                <input class="form-control" type="search" placeholder="Wyszukaj" aria-label="Search">
                <button class="btn btn-light" type="submit">Znajdź</button>

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