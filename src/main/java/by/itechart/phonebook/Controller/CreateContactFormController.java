package by.itechart.phonebook.Controller;


import by.itechart.phonebook.DTO.AttachmentDTO;
import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.DTO.TelephoneDTO;
import by.itechart.phonebook.MVC.RequestMapping;
import by.itechart.phonebook.Servic.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class CreateContactFormController {
    final static String UPLOAD_DIRECTORY_FILE = "META-INF" + File.separator + "file";
    private ContactDTO getContactDTOFileItems(List<FileItem> formItems) throws Exception {
        ContactDTO contactDTO = new ContactDTO();
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (item.isFormField()) {
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

    private TelephoneDTO getTelephoneDTOFileItems(List<FileItem> formItems) throws UnsupportedEncodingException {
        TelephoneDTO telephoneDTO = new TelephoneDTO();
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (item.isFormField() && item.getSize() > 0) {
                    switch (item.getFieldName()) {
                        case "phone_type":
                            telephoneDTO.setType(item.getString("UTF-8"));
                            break;
                        case "country_code":
                            telephoneDTO.setCountryCode(item.getString("UTF-8"));
                            break;
                        case "comment":
                            telephoneDTO.setComments(item.getString("UTF-8"));
                            break;
                        case "operator_code":
                            telephoneDTO.setOperatorCode(item.getString("UTF-8"));
                            break;
                        case "phone_number":
                            telephoneDTO.setNumber(item.getString("UTF-8"));
                            break;
                    }
                }
            }
        }
        return telephoneDTO;
    }

    private TelephoneDTO getTelephoneDTOWithIDFileItems(List<FileItem> formItems, int id) throws UnsupportedEncodingException {
        TelephoneDTO telephoneDTO = new TelephoneDTO();
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("phone_type_" + Integer.toString(id))) {
                        telephoneDTO.setType(item.getString("UTF-8"));
                        continue;
                    } else if (item.getFieldName().equals("country_code_" + Integer.toString(id))) {
                        telephoneDTO.setCountryCode(item.getString("UTF-8"));
                        continue;
                    } else if (item.getFieldName().equals("comment_" + Integer.toString(id))) {
                        telephoneDTO.setComments(item.getString("UTF-8"));
                        continue;
                    } else if (item.getFieldName().equals("operator_code_" + Integer.toString(id))) {
                        telephoneDTO.setOperatorCode(item.getString("UTF-8"));
                        continue;
                    } else if (item.getFieldName().equals("phone_number_" + Integer.toString(id))) {
                        telephoneDTO.setNumber(item.getString("UTF-8"));
                        continue;
                    }
                }
            }
        }
        return telephoneDTO;
    }

    private AttachmentDTO getAttachmentWithIdDTOFileItems(List<FileItem> formItems, int id) throws Exception {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("name_" + Integer.toString(id))) {
                        attachmentDTO.setName(item.getString("UTF-8"));
                        continue;
                    } else if (item.getFieldName().equals("attachment_comment_" + Integer.toString(id))) {
                        attachmentDTO.setComment(item.getString("UTF-8"));
                        continue;
                    }
                }
            }
        }
        return attachmentDTO;
    }

    private AttachmentDTO getAttachmentDTOFileItems(List<FileItem> formItems, HttpServletRequest req) throws Exception {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        if (formItems != null && formItems.size() > 0) {
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    if (item.getFieldName().equals("up_file") && item.getSize() > 0) {
                        String uploadPath = req.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY_FILE + File.separator + generateUniqueId();
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        attachmentDTO.setPath(filePath);
                    }
                } else if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "name":
                            attachmentDTO.setName(item.getString("UTF-8"));
                            break;
                        case "attachment_comment":
                            attachmentDTO.setComment(item.getString("UTF-8"));
                            break;
                    }
                }
            }
        }
        return attachmentDTO;
    }


    @RequestMapping(uri = "/createcontact", method = RequestMapping.Method.GET)
    public void openCreateContactForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("createContactDTO", new ContactDTO());
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/create_contact_page.jsp").forward(req, resp);
    }


    @RequestMapping(uri = "/editcontact", method = RequestMapping.Method.GET)
    public void editContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/create_contact_page.jsp").forward(req, resp);
    }


    @RequestMapping(uri = "/editcontactWithoutId", method = RequestMapping.Method.GET)
    public void editContactTset(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/create_contact_page.jsp").forward(req, resp);
    }


    @RequestMapping(uri = "/createtelephone", method = RequestMapping.Method.POST)
    public void createTelephone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        TelephoneDTO telephoneDTO = new TelephoneDTO();
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems);
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
            TelephoneDTO telephoneDTOFileItems = getTelephoneDTOFileItems(formItems);
            telephoneDTO.setId(generateUniqueId()).setCountryCode(telephoneDTOFileItems.getCountryCode()).setOperatorCode(telephoneDTOFileItems.getOperatorCode()).setNumber(telephoneDTOFileItems.getNumber()).setType(telephoneDTOFileItems.getType()).setComments(telephoneDTOFileItems.getComments());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*if (new Validator().check(telephoneDTO).hasErroe()) {
            resp.sendRedirect("/createcontactwitherror");
            return;
        }*/
        contactDTO.addTelephone(telephoneDTO);
        resp.sendRedirect("/editcontactWithoutId");
    }

    @RequestMapping(uri = "/deletetelephone", method = RequestMapping.Method.POST)
    public void deleteTelephone(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TelephoneService telephoneService = new TelephoneServiceImpl();
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        contactDTO.deleteTelephone(Integer.valueOf(req.getParameter("id")));
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems);
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/editcontactWithoutId");
    }

    @RequestMapping(uri = "/updatetelephone", method = RequestMapping.Method.POST)
    public void updateTelephone(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems);
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
            TelephoneDTO telephoneDTOWithIDFileItems = getTelephoneDTOWithIDFileItems(formItems, id);
            for (TelephoneDTO telephoneDTO : contactDTO.getTelephonesDTO()) {
                if (telephoneDTO.getId() == id) {
                    telephoneDTO.setCountryCode(telephoneDTOWithIDFileItems.getCountryCode()).setOperatorCode(telephoneDTOWithIDFileItems.getOperatorCode()).setNumber(telephoneDTOWithIDFileItems.getNumber()).setType(telephoneDTOWithIDFileItems.getType()).setComments(telephoneDTOWithIDFileItems.getComments());
                /*if (new Validator().check(telephoneDTO).hasErroe()) {
                    req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/valid_telephone_erroe_create_contact_form.jsp").forward(req, resp);
                    return;
                }*/
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/editcontactWithoutId");
    }





    private static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }


    @RequestMapping(uri = "/createattachment", method = RequestMapping.Method.POST)
    public void createAttachment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems);
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
            AttachmentDTO attachmentDTOFileItems = getAttachmentDTOFileItems(formItems, req);
            attachmentDTOFileItems.setId(generateUniqueId()).setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
            contactDTO.addAttachment(attachmentDTOFileItems);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*if (new Validator().check(telephoneDTO).hasErroe()) {
            resp.sendRedirect("/createcontactwitherror");
            return;
        }*/
        resp.sendRedirect("/editcontactWithoutId");
    }

    @RequestMapping(uri = "/updateattachment", method = RequestMapping.Method.POST)
    public void updateAttachment(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems);
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
            AttachmentDTO attachmentWithIdDTOFileItems = getAttachmentWithIdDTOFileItems(formItems, id);
            for (AttachmentDTO attachmentDTO : contactDTO.getAttachmentDTOs()) {
                if (attachmentDTO.getId() == id) {
                    attachmentDTO.setName(attachmentWithIdDTOFileItems.getName()).setComment(attachmentWithIdDTOFileItems.getComment());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/editcontactWithoutId");
    }

    @RequestMapping(uri = "/deleteattachment", method = RequestMapping.Method.POST)
    public void deleteAttachment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TelephoneService telephoneService = new TelephoneServiceImpl();
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        contactDTO.deleteAttachment(Integer.valueOf(req.getParameter("id")));
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            ContactDTO contactDTOFileItems = getContactDTOFileItems(formItems);
            contactDTO.setFirstName(contactDTOFileItems.getFirstName()).setSecondName(contactDTOFileItems.getSecondName()).setPatronymic(contactDTOFileItems.getPatronymic()).setBirthday(contactDTOFileItems.getBirthday()).setMale(contactDTOFileItems.getMale()).setNationality(contactDTOFileItems.getNationality()).setRelationshipStatus(contactDTOFileItems.getRelationshipStatus()).setWebSite(contactDTOFileItems.getWebSite()).setEmail(contactDTOFileItems.getEmail()).setCountry(contactDTOFileItems.getCountry()).setCity(contactDTOFileItems.getCity()).setStreet(contactDTOFileItems.getStreet()).setIndex(contactDTOFileItems.getIndex()).setCompany(contactDTOFileItems.getCompany());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/editcontactWithoutId");
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

    @RequestMapping(uri = "/dowloadattachment", method = RequestMapping.Method.GET)
    public void dowloadAttachment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        int id = Integer.parseInt(req.getParameter("id"));
        for (AttachmentDTO attachmentDTO: contactDTO.getAttachmentDTOs()){
            if (attachmentDTO.getId()==id){
                File my_file = new File(attachmentDTO.getPathString());
                resp.setHeader("Content-disposition","attachment; filename="+attachmentDTO.getNameString()+"."+getFileExtension(my_file));
                OutputStream out = resp.getOutputStream();
                FileInputStream in = new FileInputStream(my_file);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0){
                    out.write(buffer, 0, length);
                }
                in.close();
                out.flush();
                break;
            }
        }

    }
}
