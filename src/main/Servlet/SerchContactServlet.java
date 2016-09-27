package main.Servlet;


import main.DTO.ContactDTO;
import main.Servic.ContactService;
import main.Servic.ContactServiceImpl;
import main.Servic.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/serchcontact")
public class SerchContactServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/pages/serch_form.jsp").forward(req, resp);
    }

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
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName(fistName).setSecondName(secondName).setPatronymic(patronymice).setBirthday(birthday).setMale(sex).setNationality(nationality).setRelationshipStatus(relationshipStatus).setWebSite(webSite).setEmail(email).setCountry(country).setCity(city).setStreet(street).setIndex(index).setCompany(workPlace);
        ContactService contactService = new ContactServiceImpl();

        HttpSession session = req.getSession();
        session.setAttribute("serchPattern", contactDTO);
        session.removeAttribute("page");
        resp.sendRedirect("/");
    }
}
