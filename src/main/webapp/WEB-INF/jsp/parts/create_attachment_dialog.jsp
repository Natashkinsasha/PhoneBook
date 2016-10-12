<%@ page import="java.util.List" %>
<%@ page import="by.itechart.phonebook.DTO.ContactDTO" %>
<%@ page import="by.itechart.phonebook.DTO.TelephoneDTO" %>
<%@ page import="by.itechart.phonebook.DTO.AttachmentDTO" %><%--
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
    List<AttachmentDTO> attachmentDTOs = contactDTO.getAttachmentDTOs();
    for (AttachmentDTO attachmentDTO : attachmentDTOs) {%>
<div id="modal_attachment_<%=attachmentDTO.getId()%>" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Updtae attachment</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name_<%=attachmentDTO.getIdString()%>">Name</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="name_<%=attachmentDTO.getIdString()%>"
                           name="name_<%=attachmentDTO.getIdString()%>"
                           value="<%=attachmentDTO.getNameString()%>">
                </div>
                <div class="form-group">
                    <label for="attachment_comment_<%=attachmentDTO.getIdString()%>">Comment</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="attachment_comment_<%=attachmentDTO.getIdString()%>"
                           name="attachment_comment_<%=attachmentDTO.getIdString()%>"
                           value="<%=attachmentDTO.getCommentString()%>">
                </div>
                <div class="modal-footer">
                    <div class="btn-group" role="group" aria-label="...">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            Close
                        </button>
                        <button id="update_attachment" onclick="sbmt(this, <%=attachmentDTO.getIdString()%>)"
                                class="btn btn-success">
                            Update
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%}%>
<div id="modal_attachment" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create attachment</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name" class="required">Name</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="name"
                           name="name"
                           required>
                </div>
                <div class="form-group">
                    <label for="attachment_comment">Comment</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="attachment_comment"
                           name="attachment_comment">
                </div>
                <div class="form-group">
                    <label for="up_file" class="required">Attachment:</label>
                    <input id="up_file" type="file" name="up_file" accept="*" required>
                </div>
                <div class="modal-footer">
                    <div class="btn-group" role="group" aria-label="...">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            Close
                        </button>
                        <button id="create_attachment" onclick="sbmt(this)" class="btn btn-success">
                            Create
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
