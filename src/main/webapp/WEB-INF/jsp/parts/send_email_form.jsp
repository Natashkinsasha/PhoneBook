<%@ page import="by.itechart.phonebook.DTO.EmailDTO" %>
<%@ page import="by.itechart.phonebook.DTO.EmailTemplateDTO" %>
<%@ page import="java.util.List" %><%--
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
    List<EmailTemplateDTO> emailTemplateDTOList = (List<EmailTemplateDTO>) request.getSession().getAttribute("templates");
%>

<form method="post" action="/sendemail">
    <div class="form-group">
        <label for="whom">Whom</label>
        <input type="text" maxlength="32" class="form-control" id="whom" name="whom"
               value="<%=emailDTO.getWhomString()%>" required
               pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
    </div>
    <div class="form-group">
        <label for="whom">Theme</label>
        <input type="text" maxlength="32" class="form-control" id="theme" name="theme">
    </div>
    <div class="form-group">
        <label for="template">Template</label>
        <select type="text" class="form-control" id="template" name="template"
                onchange="document.location=this.options[this.selectedIndex].value">
            <option value="/sendemail">------</option>
            <%for (EmailTemplateDTO emailTemplateDTO : emailTemplateDTOList) {%>
            //Сделать селектед выбранный шаблон
            <option value="/sendemail?id_template=<%=emailTemplateDTO.getId()%>"><%=emailTemplateDTO.getEngName()%>
            </option>
            <%}%>
        </select>
    </div>
    <div class="form-group">
        <label for="text">Text</label>
        <textarea class="form-control" rows="7" id="text" name="text"><%=emailDTO.getTextString()%></textarea>
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="submit" class="btn btn-success">Send</button>
        <button type="button" class="btn btn-danger" onclick="location.href='/'">Сancel</button>
    </div>
</form>
</body>
</html>
