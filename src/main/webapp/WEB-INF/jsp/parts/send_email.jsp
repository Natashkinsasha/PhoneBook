<%@ page import="main.DTO.EmailDTO" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 23.09.2016
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<%
    EmailDTO emailDTO = (EmailDTO) request.getSession().getAttribute("emailDTO");
%>

<form method="post" action="/sendemail">
    <div class="form-group">
        <label for="whom">Whom</label>
        <input type="text" maxlength="32" class="form-control" id="whom" name="whom" value="<%=emailDTO.getWhomString()%>">
    </div>
    <div class="form-group">
        <label for="whom">Theme</label>
        <input type="text" maxlength="32" class="form-control" id="theme" name="theme">
    </div>
    <div class="form-group">
        <label for="template">Template</label>
        <select type="text" class="form-control" id="template" name="template">
            <option value=""></option>
            <option value="male">template 1</option>
            <option value="female">template 2</option>
        </select>
    </div>
    <div class="form-group">
        <label for="text">Text</label>
        <textarea class="form-control" rows="7" id="text" name="text" value = "<%=emailDTO.getTextString()%>"></textarea>
    </div>
</form>
</body>
</html>
