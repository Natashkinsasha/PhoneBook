<%@ page import="main.java.Servic.MainTableServiceImpl" %>
<%@ page import="main.java.DTO.ContactDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head></head>
<body>
<form id="checkbox_form" method="post" action="/deletesomecontact">
    <div class="row text-right">
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-success" onclick="location.href='/createcontact'">Create</button>
            <button type="button" class="btn btn-info" onclick="location.href='/serchcontact'">Serch</button>
            <button type="button" class="btn btn-info" onclick="location.href='/cancelserch'">Сancel search</button>
            <button id="send_email" type="button" class="btn btn-warning" onclick="location.href='/sendemail'">Send email</button>
            <button type="submit" class="btn btn-danger">Delete</button>
        </div>
    </div>
    <div class="row">
        <table class="table">

            <thead>
            <tr>
                <th><label><input type="checkbox"></label></th>
                <th>Name</th>
                <th>Birthday</th>
                <th>Mail</th>
                <th>Company</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                List<ContactDTO> contactDTOs = (List<ContactDTO>) request.getSession().getAttribute("DTOs");
                for (ContactDTO contactDTO : contactDTOs) {%>
            <tr>
                <td><label><input type="checkbox" name="<%=contactDTO.getId()%>"></label></td>
                <td>
                    <a href="/editcontact?id=<%=contactDTO.getIdString()%>"><%=contactDTO.getFirstNameString()%> <%=contactDTO.getSecondNameString()%>
                    </a>
                </td>
                <td><%=contactDTO.getBirthdayString()%>
                </td>
                <td><a href="/sendemail?id=<%=contactDTO.getId()%>"><%=contactDTO.getEmailString()%>
                </a>
                </td>
                <td><%=contactDTO.getCompanyString()%>
                </td>
                <td>
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="border: 0px">
                            <span class="glyphicon glyphicon-option-vertical"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="/deletecontact?id=<%=contactDTO.getId()%>">Delete</a></li>
                        </ul>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>

        </table>
    </div>
</form>
<div class="row text-center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="?page=<%=1%>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <%
                int pageCol = (int) request.getSession().getAttribute("pageCol");
                for (int i = 1; i <= pageCol; i++) {
            %>
            <li><a href="?page=<%=i%>"><%=i%>
            </a></li>
            <%
                }
            %>
            <li>
                <a href="?page=<%=pageCol%>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>