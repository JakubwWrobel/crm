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
        <table class="table table-striped table-bordered table-sm table-hover" id="tabDeleteEmployee">
            <tr class="table-ro">
                <th scope="col"> #</th>
                <th scope="col"> ID</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Address</th>
                <th scope="col">Note</th>
                <th scope="col">Delete</th>
            </tr>

            <c:forEach items="${employees}" var="employee">
                <%--\showallemployees?id=${employee.id}--%>
                <tr class="table-ro" data-target="#myModal" id="mydialog">
                    <th><c:out value="${id}"/></th>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.address}</td>
                    <td>${employee.note}</td>
                    <td><a href="#" class="btn btn-primary btn-sm active" role="button" data-toggle="modal" data-target="#myModal" aria-pressed="true">Primary link</a></td>
                </tr>
                <c:set var="id" value="${id + 1}" scope="request"/>
            </c:forEach>
        </table>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="employeeDelete">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                    <button type="button" id="yesButton" class="btn btn-secondary">Yes</button>
                </div>
            </div>
        </div>
    </div>

<%--    <script>
        $('#sumb2').on('click', function (e) {
            //preventDefault anuluje zatwierdzenie form oraz dodatkowoych akcji
            let ss = ${employee.id}
                console.log(e)
            e.preventDefault();
            bs4pop.confirm('Are you sure to delete that employee?', function (sure) {
            }, {
                title: 'Confirmation Dialog',
                hideRemove: true,
                btns: [
                    {
                        label: 'OK',
                        onClick(cb) {
                            let vat = "/deleteemployee?id=${employee.id}"
                            //e.target instead of vat
                            return location.href = e.target
                        }
                    },
                    {
                        label: 'CANCEL',
                        className: 'btn-secondary',
                        onClick(cb) {
                            return e.preventDefault();
                        }
                    }
                ]
            })
        })
    </script>--%>

    <label class="btn btn-secondary active">
        <input type="radio" name="options" id="option1" autocomplete="off" data-target="\update" checked> Active
    </label>


</main>

</body>
</html>


<%--https://www.codeply.com/go/tKcuKpAfc3--%>