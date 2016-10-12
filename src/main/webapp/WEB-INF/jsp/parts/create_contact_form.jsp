
<%@ page import="java.util.List" %>
<%@ page import="by.itechart.phonebook.DTO.ContactDTO" %>
<%@ page import="by.itechart.phonebook.DTO.TelephoneDTO" %>
<%@ page import="by.itechart.phonebook.DTO.AttachmentDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head></head>
<body>
<%
    ContactDTO contactDTO = (ContactDTO) request.getSession().getAttribute("createContactDTO");
%>



<form id="create_contact_form" method="post" action="/createcontact" enctype="multipart/form-data">
    <div class="form-group">
        <label for="up_photo">Photo:</label>
        <p><img src="/get_photo?photo_path=<%=contactDTO.getPhotoPathString()%>" height="200px" alt="Contact photo"></p>
        <input id="up_photo" type="file" name="up_photo" accept="image/*">
    </div>
    <div class="form-group">
        <label for="first_name">Firstname:</label>
        <input type="text" maxlength="32" required class="form-control" id="first_name" name="first_name"
               value="<%=contactDTO.getFirstNameString()%>">
    </div>
    <div class="form-group">
        <label for="second_name">Secondname:</label>
        <input type="text" maxlength="32" required class="form-control" id="second_name" name="second_name"
               value="<%=contactDTO.getSecondNameString()%>">
    </div>
    <div class="form-group">
        <label for="patronymic">Patronymic:</label>
        <input type="text" maxlength="32" class="form-control" id="patronymic" name="patronymic"
               value="<%=contactDTO.getPatronymicString()%>">
    </div>
    <div class="form-group">
        <label for="birthday">Birthday</label>
        <input type="date" class="form-control" id="birthday" name="birthday"
               value="<%=contactDTO.getBirthdayString()%>">
    </div>
    <div class="form-group">
        <label for="sex">Sex</label>
        <select type="text" class="form-control" id="sex" name="sex">
            <option value=""><%=contactDTO.getMaleString()%>
            </option>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
    </div>
    <div class="form-group">
        <label for="nationality">Nationality</label>
        <input type="text" maxlength="32" class="form-control" id="nationality" name="nationality"
               value="<%=contactDTO.getNationalityString()%>">
    </div>
    <div class="form-group">
        <label for="relationship_status">Relationship status</label>
        <input type="text" maxlength="32" class="form-control" id="relationship_status" name="relationship_status"
               value="<%=contactDTO.getRelationshipStatusString()%>">
    </div>
    <div class="form-group">
        <label for="web_site">Web site</label>
        <input type="text" maxlength="32" class="form-control" id="web_site" name="web_site"
               value="<%=contactDTO.getWebSiteString()%>">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="text" maxlength="32" class="form-control"
               pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" id="email" name="email"
               value="<%=contactDTO.getEmailString()%>">
    </div>


    <div class="form-group">
        <label for="work_place">Work place</label>
        <input type="text" maxlength="32" class="form-control" id="work_place" name="work_place"
               value="<%=contactDTO.getCompanyString()%>">
    </div>
    <div class="form-group">
        <label for="country">Country</label>
        <input type="text" maxlength="32" class="form-control" id="country" name="country"
               value="<%=contactDTO.getCountryString()%>">
    </div>
    <div class="form-group">
        <label for="city">City</label>
        <input type="text" maxlength="32" class="form-control" id="city" name="city"
               value="<%=contactDTO.getCityString()%>">
    </div>
    <div class="form-group">
        <label for="street">Street</label>
        <input type="text" maxlength="32" class="form-control" id="street" name="street"
               value="<%=contactDTO.getStreetString()%>">
    </div>
    <div class="form-group">
        <label for="index">Index</label>
        <input type="text" maxlength="32" class="form-control" id="index" name="index"
               value="<%=contactDTO.getIndexString()%>">
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th><label><input type="checkbox"></label></th>
                <th>Country code</th>
                <th>Operator code</th>
                <th>Phone number</th>
                <th>Phone type</th>
                <th>Comment</th>
                <th>
                    <button data-toggle="modal" data-target="#modal_telephone"  class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </th>
            </tr>
            </thead>
            <tbody>

            <%
                List<TelephoneDTO> telephoneDTOs = contactDTO.getTelephonesDTO();
                for (TelephoneDTO telephoneDTO : telephoneDTOs) {%>
            <tr>
                <td><label><input type="checkbox" id="<%=telephoneDTO.getId()%>"></label></td>
                <td><%=telephoneDTO.getCountryCodeString()%></td>
                <td><%=telephoneDTO.getOperatorCodeString()%></td>
                <td><%=telephoneDTO.getNumberString()%></td>
                <td><%=telephoneDTO.getTypeString()%></td>
                <td><%=telephoneDTO.getCommentsString()%></td>
                <td>
                    <button id="delete_telephone" onclick="sbmt(this, <%=telephoneDTO.getIdString()%>)" class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                    <button id="edit_telephone" data-toggle="modal" data-target="#modal_telephone_<%=telephoneDTO.getId()%>" class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>

        </table>
    </div>

    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th><label><input type="checkbox"></label></th>
                <th>Name</th>
                <th>Date</th>
                <th>Comment</th>
                <th>
                    <button data-toggle="modal" data-target="#modal_attachment"  class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </th>
            </tr>
            </thead>
            <tbody>

            <%
                List<AttachmentDTO> attachmentDTOs = contactDTO.getAttachmentDTOs();
                for (AttachmentDTO attachmentDTO : attachmentDTOs) {%>
            <tr>
                <td><label><input type="checkbox" id="<%=attachmentDTO.getId()%>"></label></td>
                <td><%=attachmentDTO.getNameString()%></td>
                <td><%=attachmentDTO.getCreationDateString()%></td>
                <td><%=attachmentDTO.getCommentString()%></td>
                <td>
                    <button id="dowload_attachment" onclick="location.href='/dowloadattachment?id=<%=attachmentDTO.getId()%>'" class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-download"></span>
                    </button>
                    <button id="delete_attachment" onclick="sbmt(this, <%=attachmentDTO.getId()%>)" class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                    <button id="edit_attachment" data-toggle="modal" data-target="#modal_attachment_<%=attachmentDTO.getId()%>" class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-pencil"></span>
                    </button>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>

        </table>
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="button" class="btn btn-danger" onclick="location.href='/'">Close</button>
        <button type="submit" class="btn btn-success">Save</button>
        <button type="button" class="btn btn-warning"
                onclick="location.href='/deletecontact?id=<%=contactDTO.getId()%>'">Delete
        </button>
    </div>
    <div class="container">
        <jsp:include
                page="/WEB-INF/jsp/parts/create_telephone_dialog.jsp"
                flush="true"/>
    </div>
    <div class="container">
        <jsp:include
                page="/WEB-INF/jsp/parts/create_attachment_dialog.jsp"
                flush="true"/>
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
                act.action = "/updatetelephone?id="+id;
                act.method = "post";
                act.submit();
            } else if (btn.id=="delete_telephone"){
                act.action = "/deletetelephone?id="+id;
                act.method = "post";
                act.submit();
            } else if (btn.id=="create_attachment"){
                act.action = "/createattachment";
                act.method = "post";
                act.submit();
            } else if (btn.id=="update_attachment"){
                act.action = "/updateattachment?id="+id;
                act.method = "post";
                act.submit();
            } else if (btn.id=="delete_attachment"){
                act.action = "/deleteattachment?id="+id;
                act.method = "post";
                act.submit();
            }
        }
    </script>
</form>


</div>

</body>
</html>