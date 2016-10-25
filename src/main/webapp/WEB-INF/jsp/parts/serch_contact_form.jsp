
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<form method="post" action="/serchcontact" novalidate>
    <input type="hidden" name="contact_id" value="0">
    <div class="form-group">
        <label for="first_name">Firstname:</label>
        <input type="text" maxlength="32" required class="form-control" id="first_name" name="first_name" value="${serchPattern.firstName}"/>
    </div>
    <div class="form-group">
        <label for="second_name">Secondname:</label>
        <input type="text" maxlength="32" required class="form-control" id="second_name" name="second_name" value="${serchPattern.secondName}"/>
    </div>
    <div class="form-group">
        <label for="patronymic">Patronymic:</label>
        <input type="text" maxlength="32" class="form-control" id="patronymic" name="patronymic" value="${serchPattern.patronymic}"/>
    </div>
    <div class="form-group">
        <label for="sex">Sex</label>
        <select type="text" class="form-control" id="sex" name="sex">
            <option value=""></option>
            <option ${serchPattern.male?'selected':''} value="male">
                Male
            </option>
            <option ${serchPattern.male==false?'selected':''} value="female">
                Female
            </option>
        </select>
    </div>
    <div class="form-group">
        <label for="nationality">Nationality</label>
        <input type="text" maxlength="32" class="form-control" id="nationality" name="nationality" value="${serchPattern.nationality}"/>
    </div>
    <div class="form-group">
        <label for="relationship_status">Relationship status</label>
        <input type="text" maxlength="32" class="form-control" id="relationship_status" name="relationship_status" value="${serchPattern.relationshipStatus}"/>
    </div>
    <div class="form-group">
        <label for="web_site">Web site</label>
        <input type="text" maxlength="32" class="form-control" id="web_site" name="web_site" value="${serchPattern.webSite}"/>
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="text" maxlength="32" class="form-control" required
               pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" id="email" name="email" value="${serchPattern.email}"/>
    </div>
    <div class="form-group">
        <label for="work_place">Work place</label>
        <input type="text" maxlength="32" class="form-control" id="work_place" name="work_place" value="${serchPattern.company}"/>
    </div>
    <div class="form-group">
        <label for="country">Country</label>
        <input type="text" maxlength="32" class="form-control" id="country" name="country" value="${serchPattern.country}"/>
    </div>
    <div class="form-group">
        <label for="city">City</label>
        <input type="text" maxlength="32" class="form-control" id="city" name="city" value="${serchPattern.city}">
    </div>
    <div class="form-group">
        <label for="street">Street</label>
        <input type="text" maxlength="32" class="form-control" id="street" name="street" value="${serchPattern.street}">
    </div>
    <div class="form-group">
        <label for="index">Index</label>
        <input type="text" maxlength="32" class="form-control" id="index" name="index" value="${serchPattern.index}">
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="button" class="btn btn-danger" onclick="location.href='/'">Close</button>
        <button type="submit" class="btn btn-info">Serch</button>
        <c:if test="${not empty serchPattern}">
            <button type="button" class="btn btn-info" onclick="location.href='/cancelserch'">Сancel search</button>
        </c:if>
    </div>
</form>
</body>
</html>
