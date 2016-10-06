package main.java.Controller;


import main.java.DTO.ContactDTO;
import main.java.MVC.RequestMapping;
import main.java.Servic.*;
import main.java.Validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class ContactsFormController {
    @RequestMapping(uri = "/",method = RequestMapping.Method.GET)
    public void mainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainTableService mainTableService = new MainTableServiceImpl();
        req.getSession().removeAttribute("createContactDTO");
        String page = req.getParameter("page");
        HttpSession session = req.getSession();
        if (page != null) {
            session.setAttribute("page", Integer.valueOf(page));
        }
        List<ContactDTO> contactDTOs = null;
        try {
            contactDTOs = mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("DTOs",contactDTOs);
        int pageCol = 0;
        try {
            pageCol = mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("pageCol",pageCol);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/contact_list_page.jsp").forward(req, resp);
    }

    @RequestMapping(uri = "/createcontact",method = RequestMapping.Method.POST)
    public void createContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
        Validator validator = new Validator();
        /*if (validator.check(contactDTO).hasErroe()){
            req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/valid_contact_erroe_create_contact_form.jsp").forward(req, resp);
            return;
        }*/
        ContactService contactService = new ContactServiceImpl();
        try {
            if (contactDTO.getId()==0) {
                contactService.createContact(contactDTO);
            } else {
                contactService.updateContact(contactDTO);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.getSession().removeAttribute("createContactDTO");
        MainTableService mainTableService = new MainTableServiceImpl();
        String page = req.getParameter("page");
        HttpSession session = req.getSession();
        if (page != null) {
            session.setAttribute("page", Integer.valueOf(page));
        }
        List<ContactDTO> contactDTOs = null;
        try {
            contactDTOs = mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("DTOs",contactDTOs);
        int pageCol = 0;
        try {
            pageCol = mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("pageCol",pageCol);
        resp.sendRedirect("/");
    }
    @RequestMapping(uri = "/deletecontact",method = RequestMapping.Method.GET)
    public void deleteOneContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ContactService contactService = new ContactServiceImpl();
        try {
            contactService.deleteContact(Integer.valueOf(req.getParameter("id")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        MainTableService mainTableService = new MainTableServiceImpl();
        req.getSession().removeAttribute("createContactDTO");
        String page = req.getParameter("page");
        HttpSession session = req.getSession();
        if (page != null) {
            session.setAttribute("page", Integer.valueOf(page));
        }
        List<ContactDTO> contactDTOs = null;
        try {
            contactDTOs = mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("DTOs",contactDTOs);
        int pageCol = 0;
        try {
            pageCol = mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("pageCol",pageCol);
        resp.sendRedirect("/");
    }


    @RequestMapping(uri = "/deletesomecontact",method = RequestMapping.Method.POST)
    public void deleteSomeContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ContactService contactService = new ContactServiceImpl();
        for (Enumeration<String> parametrs = req.getParameterNames(); parametrs.hasMoreElements();){
            try {
                String id = parametrs.nextElement();
                contactService.deleteContact(Integer.valueOf(id));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        MainTableService mainTableService = new MainTableServiceImpl();
        req.getSession().removeAttribute("createContactDTO");
        String page = req.getParameter("page");
        HttpSession session = req.getSession();
        if (page != null) {
            session.setAttribute("page", Integer.valueOf(page));
        }
        List<ContactDTO> contactDTOs = null;
        try {
            contactDTOs = mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("DTOs",contactDTOs);
        int pageCol = 0;
        try {
            pageCol = mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("pageCol",pageCol);
        resp.sendRedirect("/");
    }
    @RequestMapping(uri = "/serchcontact",method = RequestMapping.Method.POST)
    public void serchContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        MainTableService mainTableService = new MainTableServiceImpl();
        req.getSession().removeAttribute("createContactDTO");
        String page = req.getParameter("page");

        if (page != null) {
            session.setAttribute("page", Integer.valueOf(page));
        }
        List<ContactDTO> contactDTOs = null;
        try {
            contactDTOs = mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("DTOs",contactDTOs);
        int pageCol = 0;
        try {
            pageCol = mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("pageCol",pageCol);
        resp.sendRedirect("/");
    }

    @RequestMapping(uri = "/cancelserch",method = RequestMapping.Method.GET)
    public void cancelSerchContacts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("serchPattern");
        req.getSession().removeAttribute("page");
        MainTableService mainTableService = new MainTableServiceImpl();
        req.getSession().removeAttribute("createContactDTO");
        String page = req.getParameter("page");
        HttpSession session = req.getSession();
        if (page != null) {
            session.setAttribute("page", Integer.valueOf(page));
        }
        List<ContactDTO> contactDTOs = null;
        try {
            contactDTOs = mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("DTOs",contactDTOs);
        int pageCol = 0;
        try {
            pageCol = mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("pageCol",pageCol);
        resp.sendRedirect("/");
    }
}
