<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head></head>
<body>
<form>
    <div class="form-group">
        <label for="first_name">Firstname:</label>
        <input type="text" class="form-control" id="first_name">
    </div>
    <div class="form-group">
        <label for="second_name">Secondname:</label>
        <input type="text" class="form-control" id="second_name">
    </div>
    <div class="form-group">
        <label for="patronymic">Patronymic:</label>
        <input type="text" class="form-control" id="patronymic">
    </div>
    <div class="form-group">
        <label for="birthday">Birthday</label>
        <input type="date" class="form-control" id="birthday">
    </div>
    <div class="form-group">
        <label for="sex">Sex</label>
        <input type="text" class="form-control" id="sex">
    </div>
    <div class="form-group">
        <label for="nationality">Nationality</label>
        <input type="text" class="form-control" id="nationality">
    </div>
    <div class="form-group">
        <label for="relationship_status">Relationship status</label>
        <input type="text" class="form-control" id="relationship_status">
    </div>
    <div class="form-group">
        <label for="web_site">Web site</label>
        <input type="text" class="form-control" id="web_site">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/" id="email">
        <span class="help-block">Password is incorrect.</span>
    </div>
    <div class="form-group">
        <label for="work_place">Work place</label>
        <input type="text" class="form-control" id="work_place">
    </div>
    <div class="form-group">
        <label for="country">Country</label>
        <input type="text" class="form-control" id="country">
    </div>
    <div class="form-group">
        <label for="city">City</label>
        <input type="text" class="form-control" id="city">
    </div>
    <div class="form-group">
        <label for="street">Street</label>
        <input type="text" class="form-control" id="street">
    </div>
    <div class="form-group">
        <label for="work_place">Index</label>
        <input type="text" class="form-control" id="index">
    </div>
    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
    <button type="submit" class="btn btn-success">Create</button>
</form>
</body>
</html>