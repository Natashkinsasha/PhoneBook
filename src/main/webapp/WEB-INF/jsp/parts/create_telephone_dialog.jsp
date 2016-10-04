<%@ page import="main.DTO.ContactDTO" %>
<%@ page import="main.DTO.TelephoneDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 28.09.2016
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<%
    ContactDTO contactDTO = (ContactDTO) request.getSession().getAttribute("createContactDTO");
%>
<%
    List<TelephoneDTO> telephoneDTOs = contactDTO.getTelephonesDTO();
    for (TelephoneDTO telephoneDTO : telephoneDTOs) {%>
<div id="modal_<%=telephoneDTO.getId()%>" class="modalDialog">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Create telephone</h4>
        <div class="form-group">
            <label for="country_code_<%=telephoneDTO.getIdString()%>">Country code</label>
            <input type="text" maxlength="32" class="form-control"
                   id="country_code_<%=telephoneDTO.getIdString()%>"
                   name="country_code_<%=telephoneDTO.getIdString()%>"
                   value="<%=telephoneDTO.getCountryCodeString()%>"
                   pattern="^\d{3}$">
        </div>
        <div class="form-group">
            <label for="operator_code_<%=telephoneDTO.getIdString()%>">Operator code</label>
            <input type="text" maxlength="32" class="form-control"
                   id="operator_code_<%=telephoneDTO.getIdString()%>"
                   name="operator_code_<%=telephoneDTO.getIdString()%>"
                   value="<%=telephoneDTO.getOperatorCodeString()%>"
                   pattern="^\d{2}$">
        </div>
        <div class="form-group">
            <label for="phone_number_<%=telephoneDTO.getIdString()%>">Phone number</label>
            <input type="text" maxlength="32" class="form-control"
                   id="phone_number_<%=telephoneDTO.getIdString()%>"
                   name="phone_number_<%=telephoneDTO.getIdString()%>"
                   value="<%=telephoneDTO.getNumberString()%>"
                   pattern="^\d{7}$"
                   required>
        </div>
        <div class="form-group">
            <label for="phone_type_<%=telephoneDTO.getIdString()%>">Phone type</label>
            <input type="text" maxlength="32" class="form-control"
                   id="phone_type_<%=telephoneDTO.getIdString()%>"
                   name="phone_type_<%=telephoneDTO.getIdString()%>" value="<%=telephoneDTO.getTypeString()%>">
        </div>
        <div class="form-group">
            <label for="comment_<%=telephoneDTO.getIdString()%>">Comment</label>
            <input type="text" maxlength="32" class="form-control" id="comment_<%=telephoneDTO.getIdString()%>"
                   name="comment_<%=telephoneDTO.getIdString()%>" value="<%=telephoneDTO.getCommentsString()%>">
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button onclick="location.href='#close'" type="button" class="btn btn-danger"
            ">
            Close
            </button>
            <button id="update_telephone" onclick="sbmt(this, <%=telephoneDTO.getIdString()%>)"
                    class="btn btn-success">
                Update
            </button>
        </div>
    </div>
</div>
<%}%>
<div id="modal" class="modalDialog">
    <div>
        <a href="#close" title="Закрыть" class="close">X</a>
        <div class="form-group">
            <label for="country_code">Country code</label>
            <input type="text" maxlength="32" class="form-control"
                   id="country_code"
                   name="country_code"
                   pattern="^\d{3}$"
                   placeholder="375">
        </div>
        <div class="form-group">
            <label for="operator_code">Operator code</label>
            <input type="text" maxlength="32" class="form-control"
                   id="operator_code"
                   name="operator_code"
                   pattern="^\d{2}$"
                   placeholder="29">
        </div>
        <div class="form-group">
            <label for="phone_number">Phone number</label>
            <input type="text" maxlength="32" class="form-control"
                   id="phone_number"
                   name="phone_number"
                   pattern="^\d{7}$"
                   placeholder="7777777">
        </div>
        <div class="form-group">
            <label for="phone_type">Phone type</label>
            <input type="text" maxlength="32" class="form-control"
                   id="phone_type"
                   name="phone_type"
                   placeholder="Work">
        </div>
        <div class="form-group">
            <label for="comment">Comment</label>
            <input type="text" maxlength="32" class="form-control" id="comment"
                   name="comment"
                   placeholder="Call 8-16">
        </div>
        <div class="btn-group" role="group" aria-label="...">
            <button onclick="location.href='#close'" type="button" class="btn btn-danger"
            ">
            Close
            </button>
            <button id="create_telephone" onclick="sbmt(this)" class="btn btn-success">
                Create
            </button>
        </div>
    </div>
</div>


<script>
    function sbmt(btn, id) {
        var knopka = document.getElementById(btn);
        var act = document.forms["create_contact_form"];
        if (btn.id == "create_telephone") {
            act.action = "/createtelephone";
            act.method = "post";
            act.submit();
        } else if (btn.id == "update_telephone") {
            act.action = "/updatelephone?id=" + id;
            act.method = "post";
            act.submit();
        }
    }
</script>

<style>
    .modalDialog {
        position: fixed;
        font-family: Arial, Helvetica, sans-serif;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: rgba(0, 0, 0, 0.8);
        z-index: 99999;
        -webkit-transition: opacity 400ms ease-in;
        -moz-transition: opacity 400ms ease-in;
        transition: opacity 400ms ease-in;
        display: none;
        pointer-events: none;
    }

    .modalDialog:target {
        display: block;
        pointer-events: auto;
    }

    .modalDialog > div {
        width: 400px;
        position: relative;
        margin: 10% auto;
        padding: 5px 20px 13px 20px;
        border-radius: 10px;
        background: #fff;
        background: -moz-linear-gradient(#fff, #999);
        background: -webkit-linear-gradient(#fff, #999);
        background: -o-linear-gradient(#fff, #999);
    }

    .close {
        background: #606061;
        color: #FFFFFF;
        line-height: 25px;
        position: absolute;
        right: -12px;
        text-align: center;
        top: -10px;
        width: 24px;
        text-decoration: none;
        font-weight: bold;
        -webkit-border-radius: 12px;
        -moz-border-radius: 12px;
        border-radius: 12px;
        -moz-box-shadow: 1px 1px 3px #000;
        -webkit-box-shadow: 1px 1px 3px #000;
        box-shadow: 1px 1px 3px #000;
    }

    .close:hover {
        background: #00d9ff;
    }
</style>


</body>
</html>
