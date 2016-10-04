package main.Controller;


import main.DTO.ContactDTO;
import main.MVC.RequestMapping;
import main.Servic.*;
import main.Validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

public class ContactsFormController {
    @RequestMapping(uri = "/",method = RequestMapping.Method.GET)
    public String mainPage(HttpServletRequest req, HttpServletResponse resp){
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
        return "/WEB-INF/jsp/pages/contact_list.jsp";
    }

    @RequestMapping(uri = "/createcontact",method = RequestMapping.Method.POST)
    public String createContact(HttpServletRequest req, HttpServletResponse resp){
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
        if (validator.check(contactDTO).hasErroe()){
            return "/WEB-INF/jsp/pages/valid_contact_erroe_create_contact_form.jsp";
        }

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
        return "/WEB-INF/jsp/pages/contact_list.jsp";
    }
    @RequestMapping(uri = "/deletecontact",method = RequestMapping.Method.GET)
    public String deleteOneContact(HttpServletRequest req, HttpServletResponse resp){
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
        return "/WEB-INF/jsp/pages/contact_list.jsp";
    }


    @RequestMapping(uri = "/deletesomecontact",method = RequestMapping.Method.POST)
    public String deleteSomeContact(HttpServletRequest req, HttpServletResponse resp){
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
        return "/WEB-INF/jsp/pages/contact_list.jsp";
    }
    @RequestMapping(uri = "/serchcontact",method = RequestMapping.Method.POST)
    public String serchContact(HttpServletRequest req, HttpServletResponse resp){
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
        Boolean moreThanDB =false;
        if(req.getParameter("moreThanDB")!=null){
            moreThanDB = true;
        }
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName(fistName).setSecondName(secondName).setPatronymic(patronymice).setBirthday(birthday).setMale(sex).setNationality(nationality).setRelationshipStatus(relationshipStatus).setWebSite(webSite).setEmail(email).setCountry(country).setCity(city).setStreet(street).setIndex(index).setCompany(workPlace).setMoreThanBD(moreThanDB);
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
        return "/WEB-INF/jsp/pages/contact_list.jsp";
    }

    @RequestMapping(uri = "/cancelserch",method = RequestMapping.Method.GET)
    public String cancelSerchContacts(HttpServletRequest req, HttpServletResponse resp){
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
        return "/WEB-INF/jsp/pages/contact_list.jsp";
    }
}
