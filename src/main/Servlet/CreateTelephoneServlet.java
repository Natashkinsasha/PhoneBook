package main.Servlet;


import main.DTO.ContactDTO;
import main.DTO.TelephoneDTO;
import main.Servic.TelephoneService;
import main.Servic.TelephoneServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/createtelephone")
public class CreateTelephoneServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fistName = req.getParameter("first_name");
        String secondName = req.getParameter("second_name");
        String patronymice = req.getParameter("patronymic");
        String birthday = req.getParameter("birthday");
        String sex = req.getParameter("sex");
        String nationality = req.getParameter("nationality");
        String relationshipStatus = req.getParameter("relationship_status");
        String webSite = req.getParameter("web_site");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String workPlace = req.getParameter("work_place");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String index = req.getParameter("index");
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        contactDTO.setFirstName(fistName).setSecondName(secondName).setPatronymic(patronymice).setBirthday(birthday).setMale(sex).setNationality(nationality).setRelationshipStatus(relationshipStatus).setWebSite(webSite).setEmail(email).setCountry(country).setCity(city).setStreet(street).setIndex(index).setCompany(workPlace);
        String countryCode = req.getParameter("country_code");
        String operatorCode = req.getParameter("operator_code");
        String phoneNumber = req.getParameter("phone_number");
        String phoneType = req.getParameter("phone_type");
        String comment = req.getParameter("comment");
        TelephoneDTO telephoneDTO = new TelephoneDTO();
        telephoneDTO.setCountryCode(countryCode).setOperatorCode(operatorCode).setNumber(phoneNumber).setType(phoneType).setComments(comment);
        contactDTO.addTelephone(telephoneDTO);
        resp.sendRedirect("/createcontact");
    }
}
