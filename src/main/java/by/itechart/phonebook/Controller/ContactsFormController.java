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
    public void mainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainTableService mainTableService = new MainTableServiceImpl();
        String page = req.getParameter("page");
        if (page != null) {
            req.getSession().setAttribute("page", Integer.valueOf(req.getParameter("page")));
        }
        try {
            req.getSession().setAttribute("DTOs", mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null));
            req.getSession().setAttribute("pageCol", mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern")));
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error(e);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/contact_list_page.jsp").forward(req, resp);
    }

    final static String UPLOAD_DIRECTORY_PHOTO = "META-INF" + File.separator + "photo";


    Map<String, String> parseFormItems(List<FileItem> fileItems) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        if (fileItems == null || fileItems.size() <= 0) {
            return map;
        } else {
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField() && fileItem.getSize() > 0) {
                    map.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
                } else {

                }
            }
        }
        return null;
    }


    @RequestMapping(uri = "/createcontact", method = RequestMapping.Method.POST)
    public void createContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems, req);
            Validator validator = new Validator();
            if (validator.check(contactDTOFileItems).hasErroe()) {
                req.getSession().setAttribute("error", "Enter a valid date");
                resp.sendRedirect("/editcontact");
                return;
            }
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany()).setPhotoPath(contactDTOFileItems.getPhotoPath());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }


        ContactService contactService = new ContactServiceImpl();
        try {
            if (contactDTO.getId() == 0) {
                contactService.createContact(contactDTO);
            } else {
                contactService.updateContact(contactDTO);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error(e);
        }

        resp.sendRedirect("/");
    }


    @RequestMapping(uri = "/deletecontact", method = RequestMapping.Method.GET)
    public void deleteOneContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        ContactService contactService = new ContactServiceImpl();
        contactService.deleteContact(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect("/");
    }


    @RequestMapping(uri = "/deletesomecontact", method = RequestMapping.Method.POST)
    public void deleteSomeContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        ContactService contactService = new ContactServiceImpl();
        contactService.deleteContact(ArrayUtils.toObject(Arrays.stream(req.getParameterValues("contact_table")).mapToInt(Integer::parseInt).toArray()));
        resp.sendRedirect("/");
    }

    @RequestMapping(uri = "/serchcontact", method = RequestMapping.Method.POST)
    public void serchContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems, req);
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("serchPattern", contactDTO);
        req.getSession().removeAttribute("page");
        resp.sendRedirect("/");
    }

    @RequestMapping(uri = "/cancelserch", method = RequestMapping.Method.GET)
    public void cancelSerchContacts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("serchPattern");
        req.getSession().removeAttribute("page");
        resp.sendRedirect("/");
    }


    private ContactDTO getContactDTOFileItems(List<FileItem> formItems, HttpServletRequest req) throws Exception {
        ContactDTO contactDTO = new ContactDTO();
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    if (item.getFieldName().equals("up_photo") && item.getSize() > 0) {
                        String uploadPath = req.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY_PHOTO + File.separator + generateUniqueId();
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        contactDTO.setPhotoPath(filePath);
                    }
                } else if (item.isFormField() && item.getSize() > 0) {
                    switch (item.getFieldName()) {
                        case "first_name":
                            contactDTO.setFirstName(item.getString("UTF-8"));
                            break;
                        case "second_name":
                            contactDTO.setSecondName(item.getString("UTF-8"));
                            break;
                        case "patronymic":
                            contactDTO.setPatronymic(item.getString("UTF-8"));
                            break;
                        case "birthday":
                            contactDTO.setBirthday(item.getString("UTF-8"));
                            break;
                        case "sex":
                            contactDTO.setMale(item.getString("UTF-8"));
                            break;
                        case "nationality":
                            contactDTO.setNationality(item.getString("UTF-8"));
                            break;
                        case "relationship_status":
                            contactDTO.setRelationshipStatus(item.getString("UTF-8"));
                            break;
                        case "web_site":
                            contactDTO.setWebSite(item.getString("UTF-8"));
                            break;
                        case "email":
                            contactDTO.setEmail(item.getString("UTF-8"));
                            break;
                        case "country":
                            contactDTO.setCountry(item.getString("UTF-8"));
                            break;
                        case "work_place":
                            contactDTO.setCompany(item.getString("UTF-8"));
                            break;
                        case "city":
                            contactDTO.setCity(item.getString("UTF-8"));
                            break;
                        case "street":
                            contactDTO.setStreet(item.getString("UTF-8"));
                            break;
                        case "index":
                            contactDTO.setIndex(item.getString("UTF-8"));
                            break;
                    }
                }
            }
        }
        return contactDTO;
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
