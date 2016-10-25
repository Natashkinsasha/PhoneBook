package by.itechart.phonebook.Controller;


import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.MVC.RequestMapping;
import by.itechart.phonebook.Servis.*;
import by.itechart.phonebook.Validator.Validator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ContactsFormController {
    private final static Logger log = Logger.getLogger(ContactsFormController.class);

    @RequestMapping(uri = "/", method = RequestMapping.Method.GET)
    public void mainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        MainTableService mainTableService = new MainTableServiceImpl();
        String page = req.getParameter("page");
        if (page != null) {
            req.getSession().setAttribute("page", Integer.valueOf(req.getParameter("page")));
        }
        req.getSession().setAttribute("DTOs", mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null));
        req.getSession().setAttribute("pageCol", mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern")));
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/contact_list_page.jsp").forward(req, resp);
    }


    @RequestMapping(uri = "/deletecontact", method = RequestMapping.Method.GET)
    public void deleteOneContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException, NumberFormatException {
        ContactService contactService = new ContactServiceImpl();
        Integer id = Integer.valueOf(req.getParameter("id"));
        contactService.deleteContact(id);
        resp.sendRedirect("/");
    }


    @RequestMapping(uri = "/deletesomecontact", method = RequestMapping.Method.POST)
    public void deleteSomeContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException, NumberFormatException {
        ContactService contactService = new ContactServiceImpl();
        contactService.deleteContact(ArrayUtils.toObject(Arrays.stream(req.getParameterValues("contact_table")).mapToInt(Integer::parseInt).toArray()));
        resp.sendRedirect("/");
    }


    @RequestMapping(uri = "/cancelserch", method = RequestMapping.Method.GET)
    public void cancelSerchContacts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("serchPattern");
        req.getSession().removeAttribute("page");
        resp.sendRedirect("/");
    }


    private static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = new Integer(uid).toString();
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
