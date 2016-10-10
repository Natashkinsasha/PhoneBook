
<%@ page import="java.util.List" %>
<%@ page import="by.itechart.phonebook.DTO.ContactDTO" %>
<%@ page import="by.itechart.phonebook.DTO.TelephoneDTO" %>
<%--
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
<div id="modal_<%=telephoneDTO.getId()%>" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create telephone</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="country_code_<%=telephoneDTO.getIdString()%>">Country code</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="country_code_<%=telephoneDTO.getIdString()%>"
                           name="country_code_<%=telephoneDTO.getIdString()%>"
                           value="<%=telephoneDTO.getCountryCodeString()%>">
                </div>
                <div class="form-group">
                    <label for="operator_code_<%=telephoneDTO.getIdString()%>">Operator code</label>
                    <input type="text" maxlength="32" class="form-control" id="operator_code_<%=telephoneDTO.getIdString()%>"
                           name="operator_code_<%=telephoneDTO.getIdString()%>"
                           value="<%=telephoneDTO.getOperatorCodeString()%>">
                </div>
                <div class="form-group">
                    <label for="phone_number_<%=telephoneDTO.getIdString()%>">Phone number</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="phone_number_<%=telephoneDTO.getIdString()%>"
                           name="phone_number_<%=telephoneDTO.getIdString()%>"
                           value="<%=telephoneDTO.getNumberString()%>">
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
                <div class="modal-footer">
                    <div class="btn-group" role="group" aria-label="...">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            Close
                        </button>
                        <button id="update_telephone"onclick="sbmt(this, <%=telephoneDTO.getIdString()%>)" class="btn btn-success">
                            Update
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
        <%}%>
<div id="modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create telephone</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="country_code">Country code</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="country_code"
                           name="country_code">
                </div>
                <div class="form-group">
                    <label for="operator_code">Operator code</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="operator_code"
                           name="operator_code">
                </div>
                <div class="form-group">
                    <label for="phone_number">Phone number</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="phone_number"
                           name="phone_number">
                </div>
                <div class="form-group">
                    <label for="phone_type">Phone type</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="phone_type"
                           name="phone_type">
                </div>
                <div class="form-group">
                    <label for="comment">Comment</label>
                    <input type="text" maxlength="32" class="form-control" id="comment"
                           name="comment">
                </div>
                <div class="modal-footer">
                    <div class="btn-group" role="group" aria-label="...">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            Close
                        </button>
                        <button id="create_telephone" onclick="sbmt(this)" class="btn btn-success">
                            Create
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


    <script>
        function sbmt(btn, id) {
            var knopka = document.getElementById(btn);
            var act = document.forms["create_contact_form"];
            if (btn.id=="create_telephone"){
                act.action = "/createtelephone";
                act.method = "post";
                act.submit();
            } else if (btn.id=="update_telephone"){
                act.action = "/updatelephone?id="+id;
                act.method = "post";
                act.submit();
            } if (btn.id=="delete_telephone"){
                act.action = "/deletetelephone?id="+id;
                act.method = "post";
                act.submit();
            }
        }
    </script>
</body>
</html>
