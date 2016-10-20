<%@ page import="java.util.List" %>
<%@ page import="by.itechart.phonebook.DTO.ContactDTO" %>
<%@ page import="by.itechart.phonebook.DTO.TelephoneDTO" %>
<%@ page import="by.itechart.phonebook.DTO.AttachmentDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>

</head>
<body>

<div class="container">
    <form novalidate id="contact_form" method="post" action="/createcontact" enctype="multipart/form-data" acceptcharset="UTF-8">
        <div class="row">
            <div class="col-xs-4">
                <div class="form-group">
                    <label for="up_photo">Photo:</label>
                    <p><img id="photo" src="/get_photo?photo_path=${createContactDTO.photoPath}" height="200px"
                            alt="Contact photo">
                    </p>
                    <input id="up_photo" type="file" name="up_photo" accept="image/*"
                           onchange="choosePhoto(event,'photo')">
                </div>
            </div>
            <input type="hidden" name="contact_id" value="${createContactDTO.id}">
            <div class="col-xs-8">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="first_name" class="required">Firstname:</label>
                            <input type="text" maxlength="32" required class="form-control" id="first_name"
                                   name="first_name"
                                   value="${createContactDTO.firstName}">
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="second_name" class="required">Secondname:</label>
                            <input type="text" maxlength="32" required class="form-control" id="second_name"
                                   name="second_name"
                                   value="${createContactDTO.secondName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="patronymic">Patronymic:</label>
                            <input type="text" maxlength="32" class="form-control" id="patronymic" name="patronymic"
                                   value="${createContactDTO.patronymic}">
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="birthday">Birthday</label>
                            <input type="date" class="form-control" id="birthday" name="birthday"
                                   value="${createContactDTO.birthday}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="sex">Sex</label>
                            <select type="text" class="form-control" id="sex" name="sex">
                                <option value=""></option>
                                <option ${createContactDTO.male?'selected':''} value="male">
                                    Male
                                </option>
                                <option ${createContactDTO.male==false?'selected':''} value="female">
                                    Female
                                </option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="nationality">Nationality</label>
                            <input type="text" maxlength="32" class="form-control" id="nationality"
                                   name="nationality"
                                   value="${createContactDTO.nationality}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="relationship_status">Relationship status</label>
                            <input type="text" maxlength="32" class="form-control" id="relationship_status"
                                   name="relationship_status"
                                   value="${createContactDTO.relationshipStatus}">
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="web_site">Web site</label>
                            <input type="text" maxlength="32" class="form-control" id="web_site" name="web_site"
                                   value="${createContactDTO.webSite}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" maxlength="32" class="form-control"
                                   pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" id="email" name="email"
                                   value="${createContactDTO.email}">
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group">
                            <label for="work_place">Work place</label>
                            <input type="text" maxlength="32" class="form-control" id="work_place" name="work_place"
                                   value="${createContactDTO.company}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-3">
                <div class="form-group">
                    <label for="country">Country</label>
                    <input type="text" maxlength="32" class="form-control" id="country" name="country"
                           value="${createContactDTO.country}">
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" maxlength="32" class="form-control" id="city" name="city"
                           value="${createContactDTO.city}">
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-group">
                    <label for="street">Street</label>
                    <input type="text" maxlength="32" class="form-control" id="street" name="street"
                           value="${createContactDTO.street}">
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-group">
                    <label for="index">Index</label>
                    <input type="text" maxlength="32" class="form-control" id="index" name="index"
                           value="${createContactDTO.index}">
                </div>
            </div>
        </div>
    </form>
    <form  enctype="multipart/form-data" id="files_form" hidden>
    </form>
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
                    <button id="create_telephone_modal" data-toggle="modal" data-target="#modal_telephone"
                            onclick="create_telephone(this)"
                            class="btn btn-default "
                            type="button"
                            aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </th>
            </tr>
            </thead>
            <tbody id="telephones">
            <c:forEach items="${createContactDTO.telephonesDTO}" var="telephone">
                <tr id="telephone_${telephone.id}">
                    <td><label><input type="checkbox"></label></td>
                    <td id="telephone_${telephone.id}_country_code">${telephone.countryCode}</td>
                    <td id="telephone_${telephone.id}_operator_code">${telephone.operatorCode}</td>
                    <td id="telephone_${telephone.id}_number">${telephone.number}</td>
                    <td id="telephone_${telephone.id}_type">${telephone.type}</td>
                    <td id="telephone_${telephone.id}_comments">${telephone.comments}</td>
                    <td>
                        <button id="delete_telephone" onclick="delete_telephone('${telephone.id}','Are you sure?')"
                                class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true"
                                style="border: 0px">
                            <span class="glyphicon glyphicon-trash"></span>
                        </button>
                        <button id="edit_telephone" data-toggle="modal" data-target="#modal_telephone" onclick="edit_telephone(this, '${telephone.id}')"
                                class="btn btn-default "
                                type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px" >
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                    </td>
                </tr>
            </c:forEach>
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
                    <button data-toggle="modal" data-target="#modal_attachment" onclick="create_attachment(this)"
                            class="btn btn-default"
                            type="button"
                            aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </th>
            </tr>
            </thead>
            <tbody id="attachments">

            <c:forEach var="attachment" items="${createContactDTO.attachmentDTOs}">
                <tr id="attachment_${attachment.id}">
                    <td><label><input type="checkbox"></label></td>
                    <td id="attachment_${attachment.id}_name">${attachment.name}</td>
                    <td id="attachment_${attachment.id}_creationDate">${attachment.creationDate}</td>
                    <td id="attachment_${attachment.id}_comment">${attachment.comment}</td>
                    <td>
                        <button id="dowload_attachment"
                                onclick="location.href='/dowloadattachment?id=${attachment.id}'"
                                class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true"
                                style="border: 0px">
                            <span class="glyphicon glyphicon-download"></span>
                        </button>
                        <button id="delete_attachment" onclick="delete_attachment('${attachment.id}', 'Are you sure?')"
                                class="btn btn-default " type="button" aria-haspopup="true" aria-expanded="true"
                                style="border: 0px">
                            <span class="glyphicon glyphicon-trash"></span>
                        </button>
                        <button id="edit_attachment" data-toggle="modal"
                                data-target="#modal_attachment" onclick="edit_attachment(this, '${attachment.id}')"
                                class="btn btn-default "
                                type="button" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="button" class="btn btn-danger" onclick="location.href='/'">Close</button>
        <button id="create_contact" type="button" class="btn btn-success" onclick="save_contact()">Save</button>
        <button type="button" class="btn btn-warning"
                onclick="deletecontact('${createContactDTO.id}','Are you sure?')">Delete
        </button>
    </div>
    <jsp:include page="/WEB-INF/jsp/parts/create_telephone_dialog.jsp" flush="true"/>
    <jsp:include page="/WEB-INF/jsp/parts/create_attachment_dialog.jsp" flush="true"/>
</div>

</div>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/create_contact_form.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/modal.js"></script>
</body>
</html>