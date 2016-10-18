<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head></head>
<body>
<form id="checkbox_form" method="post" action="/deletesomecontact">
    <div class="row text-right">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success" onclick="location.href='/createcontact'">Create</button>
            <button type="button" class="btn btn-info" onclick="location.href='/serchcontact'">Serch</button>
            <button type="button" class="btn btn-info" onclick="location.href='/cancelserch'">Сancel search</button>
            <button id="send_email" type="button" class="btn btn-warning" onclick="location.href='/sendemail'">Sendemail</button>
            <button type="submit" class="btn btn-danger">Delete</button>
        </div>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th><label><input type="checkbox"></label></th>
                <th>Name</th>
                <th>Birthday</th>
                <th>Mail</th>
                <th>Company</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="contact" items="${DTOs}">
                <tr>
                    <td><label><input type="checkbox" name="${contact.id}"></label></td>
                    <td>
                        <a href="/editcontact?id=${contact.id}">${contact.firstName} ${contact.secondName}</a>
                    </td>
                    <td>${contact.birthday}</td>
                    <td><a href="/sendemail?id=${contact.id}">${contact.email}</a>
                    </td>
                    <td>${contact.company}</td>
                    <td>
                        <button id="delete_contact" onclick="location.href='/deletecontact?id=${contact.id}'"
                                class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true"
                                style="border: 0px">
                            <span class="glyphicon glyphicon-trash"></span>
                        </button>
                        <button id="edit_contact"
                                onclick="location.href='/editcontact?id=${contact.id}'"
                                class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true"
                                style="border: 0px">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>
<div class="row text-center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="?page=<%=1%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach var="i" begin="1" end="${pageCol}">
                <li><a href="?page=${i}">${i}</a></li>
            </c:forEach>
            <li>
                <a href="?page=${pageCol}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>