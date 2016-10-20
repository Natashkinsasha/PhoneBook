
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<form method="post" action="/sendemail">
    <div class="form-group">
        <label for="whom">Whom</label>
        <input type="text" maxlength="32" class="form-control" id="whom" name="whom"
               value="${emailDTO.whom}" required
               pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
    </div>
    <div class="form-group">
        <label for="whom">Theme</label>
        <input type="text" maxlength="32" class="form-control" id="theme" name="theme" value="${emailDTO.subject}">
    </div>

    <div class="form-group">
        <label for="templatePath">Template</label>
        <select type="text" class="form-control" id="templatePath" name="templatePath"
                onchange="document.location=this.options[this.selectedIndex].value">
            <c:forEach items="${templates}" var="template">
                <option value="/sendemail?id_template=${template.id}" ${template.id eq idChooseTemplate?'selected':''}>${template.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="text">Text</label>
        <textarea class="form-control" rows="7" id="text" name="text">${emailDTO.text}</textarea>
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="submit" class="btn btn-success">Send</button>
        <button type="button" class="btn btn-danger" onclick="location.href='/'">Сancel</button>
    </div>
</form>
</body>
</html>
