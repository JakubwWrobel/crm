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

    <title>Delete Employee</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body id="employee">
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
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Address</th>
                <th scope="col">Note</th>
                <th scope="col">Delete</th>
            </tr>

            <c:forEach items="${employees}" var="employee">

                <tr class="table-row" data-href="#confirm-delete">
                    <th><c:out value="${id}"/></th>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.address}</td>
                    <td>${employee.note}</td>
                    <td><a href="#" data-record-id="${employee.id}" data-record-title="The first one" data-toggle="modal" data-target="#confirm-delete">
                        Delete "The first one", #23
                    </a></td>
                </tr>
                <c:set var="id" value="${id + 1}" scope="request"/>
            </c:forEach>
        </table>
    </div>

    <a href="#myModal" role="button" class="btn" data-toggle="modal">Launch demo modal</a>
    <div class="p-2" id="formResults"></div>

    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 id="myModalLabel">Modal header</h3>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <form id="myForm" method="post" action="${pageContext.request.contextPath}/createemployee">
                        <input type="hidden" value="hello" id="myField" name="myField">
                        <button id="myFormSubmit" class="btn" type="submit">Submit</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                    <button class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</main>

</body>
</html>


<%--https://www.codeply.com/go/tKcuKpAfc3--%>