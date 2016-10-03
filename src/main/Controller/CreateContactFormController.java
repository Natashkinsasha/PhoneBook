package main.Controller;


import main.DTO.ContactDTO;
import main.DTO.TelephoneDTO;
import main.MVC.RequestMapping;
import main.Servic.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CreateContactFormController {

    @RequestMapping(value = "/page/createcontact", method = RequestMapping.Method.GET)
    public String openCreateContactForm(HttpServletRequest req, HttpServletResponse resp) {

        req.getSession().setAttribute("createContactDTO", new ContactDTO());

        return "/WEB-INF/jsp/pages/create_contact_form.jsp";
    }

    @RequestMapping(value = "/page/createtelephone", method = RequestMapping.Method.POST)
    public String createTelephone(HttpServletRequest req, HttpServletResponse resp) {
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
        if (req.getSession().getAttribute("createContactDTO") == null) {
            req.getSession().setAttribute("createContactDTO", new ContactDTO());
        }
        return "/WEB-INF/jsp/pages/create_contact_form.jsp";
    }

    @RequestMapping(value = "/page/deletetelephone", method = RequestMapping.Method.GET)
    public String deleteTelephone(HttpServletRequest req, HttpServletResponse resp) {
        TelephoneService telephoneService = new TelephoneServiceImpl();
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        contactDTO.deleteTelephone(Integer.valueOf(req.getParameter("id")));
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
        contactDTO.setFirstName(fistName).setSecondName(secondName).setPatronymic(patronymice).setBirthday(birthday).setMale(sex).setNationality(nationality).setRelationshipStatus(relationshipStatus).setWebSite(webSite).setEmail(email).setCountry(country).setCity(city).setStreet(street).setIndex(index).setCompany(workPlace);
        if (req.getSession().getAttribute("createContactDTO") == null) {
            req.getSession().setAttribute("createContactDTO", new ContactDTO());
        }
        return "/WEB-INF/jsp/pages/create_contact_form.jsp";
    }

    @RequestMapping(value = "/page/updatelephone", method = RequestMapping.Method.POST)
    public String updateTelephone(HttpServletRequest req, HttpServletResponse resp) {
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
        int id = Integer.parseInt(req.getParameter("id"));
        contactDTO.setFirstName(fistName).setSecondName(secondName).setPatronymic(patronymice).setBirthday(birthday).setMale(sex).setNationality(nationality).setRelationshipStatus(relationshipStatus).setWebSite(webSite).setEmail(email).setCountry(country).setCity(city).setStreet(street).setIndex(index).setCompany(workPlace);
        String countryCode = req.getParameter("country_code_" + id);
        String operatorCode = req.getParameter("operator_code_" + id);
        String phoneNumber = req.getParameter("phone_number_" + id);
        String phoneType = req.getParameter("phone_type_" + id);
        String comment = req.getParameter("comment_" + id);
        for (TelephoneDTO telephoneDTO : contactDTO.getTelephonesDTO()) {
            if (telephoneDTO.getId() == id) {
                telephoneDTO.setCountryCode(countryCode).setOperatorCode(operatorCode).setNumber(phoneNumber).setType(phoneType).setComments(comment);
                break;
            }
        }
        return "/WEB-INF/jsp/pages/create_contact_form.jsp";
    }


    @RequestMapping(value = "/page/editcontact", method = RequestMapping.Method.GET)
    public String editContact(HttpServletRequest req, HttpServletResponse resp) {
        TelephoneService telephoneService = new TelephoneServiceImpl();
        ContactService contactService = new ContactServiceImpl();
        ContactDTO contactDTO = null;
        try {
            contactDTO = contactService.getContactById(Integer.valueOf(req.getParameter("id")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("createContactDTO", contactDTO);
        if (req.getSession().getAttribute("createContactDTO") == null) {
            req.getSession().setAttribute("createContactDTO", new ContactDTO());
        }
        return "/WEB-INF/jsp/pages/create_contact_form.jsp";
    }

    final static String UPLOAD_DIRECTORY = "META-INF"+File.separator+"photo";
    @RequestMapping(value = "/page/download_photo", method = RequestMapping.Method.POST)
    public String downloadPhoto(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!ServletFileUpload.isMultipartContent(req)){
            PrintWriter writer = resp.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            if (req.getSession().getAttribute("createContactDTO") == null) {
                req.getSession().setAttribute("createContactDTO", new ContactDTO());
            }
            return "WEB-INF/jsp/pages/create_contact_form.jsp";
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String uploadPath = req.getServletContext().getRealPath("")+ File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
            if (formItems!=null&&formItems.size()>0){
                for(FileItem item: formItems){
                    if(!item.isFormField()){
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath+File.separator+fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        contactDTO.setPhotoPath(filePath);
                        req.setAttribute("message", "Upload has been done successfully");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Error");
        }
        if (req.getSession().getAttribute("createContactDTO") == null) {
            req.getSession().setAttribute("createContactDTO", new ContactDTO());
        }
        return "/WEB-INF/jsp/pages/create_contact_form.jsp";
    }
}
