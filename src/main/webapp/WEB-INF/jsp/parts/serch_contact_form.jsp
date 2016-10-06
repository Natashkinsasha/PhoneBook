<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 23.09.2016
  Time: 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form method="post" action="/serchcontact" novalidate>
    <div class="form-group">
        <label for="first_name">Firstname:</label>
        <input type="text" maxlength="32" required class="form-control" id="first_name" name="first_name">
    </div>
    <div class="form-group">
        <label for="second_name">Secondname:</label>
        <input type="text" maxlength="32" required class="form-control" id="second_name" name="second_name">
    </div>
    <div class="form-group">
        <label for="patronymic">Patronymic:</label>
        <input type="text" maxlength="32" class="form-control" id="patronymic" name="patronymic">
    </div>
    <div class="form-group">
        <label for="birthday">Birthday</label>
        <input type="date" class="form-control" id="birthday" name="birthday">
    </div>
    <div class="form-group">
        <label for="sex">Sex</label>
        <select type="text" class="form-control" id="sex" name="sex">
            <option value=""></option>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
    </div>
    <div class="form-group">
        <label for="nationality">Nationality</label>
        <input type="text" maxlength="32" class="form-control" id="nationality" name="nationality">
    </div>
    <div class="form-group">
        <label for="relationship_status">Relationship status</label>
        <input type="text" maxlength="32" class="form-control" id="relationship_status" name="relationship_status">
    </div>
    <div class="form-group">
        <label for="web_site">Web site</label>
        <input type="text" maxlength="32" class="form-control" id="web_site" name="web_site">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="text" maxlength="32" class="form-control" required
               pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" id="email" name="email">
    </div>
    <div class="form-group">
        <label for="work_place">Work place</label>
        <input type="text" maxlength="32" class="form-control" id="work_place" name="work_place">
    </div>
    <div class="form-group">
        <label for="country">Country</label>
        <input type="text" maxlength="32" class="form-control" id="country" name="country">
    </div>
    <div class="form-group">
        <label for="city">City</label>
        <input type="text" maxlength="32" class="form-control" id="city" name="city">
    </div>
    <div class="form-group">
        <label for="street">Street</label>
        <input type="text" maxlength="32" class="form-control" id="street" name="street">
    </div>
    <div class="form-group">
        <label for="index">Index</label>
        <input type="text" maxlength="32" class="form-control" id="index" name="index">
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="button" class="btn btn-danger" onclick="location.href='/'">Close</button>
        <button type="submit" class="btn btn-info">Serch</button>
    </div>
</form>
</body>
</html>
