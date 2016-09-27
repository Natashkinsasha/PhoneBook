<%@ page import="main.DTO.ContactDTO" %>
<%@ page import="main.DTO.TelephoneDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head></head>
<body>

<form id="create_contact_form" method="post" action="/createcontact" novalidate>
    <%
        ContactDTO contactDTO = (ContactDTO) request.getSession().getAttribute("createContactDTO");
    %>
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
        <input type="text" maxlength="32" class="form-control" required
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
                    <button type="button" class="btn" data-toggle="modal" data-target="#myModal" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    </button>
                </th>
            </tr>
            </thead>
            <tbody>

            <%
                List<TelephoneDTO> telephoneDTOs = contactDTO.getTelephonesDTO();
                for (TelephoneDTO telephoneDTO : telephoneDTOs) {%>
            <tr>
                <td><label><input type="checkbox" id="<%=contactDTO.getId()%>"></label></td>
                <td><%=telephoneDTO.getCountryCodeString()%>
                </td>
                <td><%=telephoneDTO.getOperatorCodeString()%>
                </td>
                <td><%=telephoneDTO.getNumberString()%>
                </td>
                <td><%=telephoneDTO.getTypeString()%>
                </td>
                <td><%=telephoneDTO.getCommentsString()%>
                </td>
                <td>
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                            <span class="glyphicon glyphicon-option-vertical"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="/deletetelephone?id=<%=telephoneDTO.getId()%>">Delete</a></li>
                            <li><a href="" data-toggle="modal" data-target="#myModal">Edit</a></li>
                        </ul>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Create telephone</h4>
                        </div>

                        <div class="modal-body">
                            <div class="form-group">
                                <label for="country_code">Country code</label>
                                <input type="text" maxlength="32" class="form-control" id="country_code"
                                       name="country_code">
                            </div>
                            <div class="form-group">
                                <label for="operator_code">Operator code</label>
                                <input type="text" maxlength="32" class="form-control" id="operator_code"
                                       name="operator_code">
                            </div>
                            <div class="form-group">
                                <label for="phone_number">Phone number</label>
                                <input type="text" maxlength="32" class="form-control" id="phone_number"
                                       name="phone_number">
                            </div>
                            <div class="form-group">
                                <label for="phone_type">Phone type</label>
                                <input type="text" maxlength="32" class="form-control" id="phone_type"
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
                                    <button onclick="sbmt(this)" class="btn btn-success">
                                        Create
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </table>
    </div>
    <div class="btn-group" role="group" aria-label="...">
        <button type="button" class="btn btn-danger" onclick="location.href='/'">Close</button>
        <button type="submit" class="btn btn-success">Save</button>
        <button type="button" class="btn btn-warning"
                onclick="location.href='/deleteservlet?id=<%=contactDTO.getId()%>'">Delete
        </button>
    </div>
</form>


<script>

    function sbmt(btn) {
        var knopka = document.getElementById(btn);
        var act = document.forms["create_contact_form"];
        act.action = "/createtelephone";
        act.method = "post";
        act.submit();
    }
</script>


</div>

</body>
</html>