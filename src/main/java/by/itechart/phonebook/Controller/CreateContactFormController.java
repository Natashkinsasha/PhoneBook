package by.itechart.phonebook.Controller;


import by.itechart.phonebook.DTO.AttachmentDTO;
import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.DTO.TelephoneDTO;
import by.itechart.phonebook.MVC.RequestMapping;
import by.itechart.phonebook.Servis.*;
import by.itechart.phonebook.Validator.Validator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

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
    private final static Logger log = Logger.getLogger(CreateContactFormController.class);
    final static String UPLOAD_DIRECTORY_FILE = "META-INF" + File.separator + "file";

    @RequestMapping(uri = "/createcontact", method = RequestMapping.Method.GET)
    public void openCreateContactForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("createContactDTO", new ContactDTO());
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/create_contact_page.jsp").forward(req, resp);
    }


    @RequestMapping(uri = "/editcontact", method = RequestMapping.Method.GET)
    public void editContact(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException, NumberFormatException {
        ContactService contactService = new ContactServiceImpl();
        ContactDTO contactDTO = contactService.getContactById(Integer.valueOf(req.getParameter("id")));
        req.getSession().setAttribute("createContactDTO", contactDTO);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/create_contact_page.jsp").forward(req, resp);
    }


    @RequestMapping(uri = "/editcontactWithoutId", method = RequestMapping.Method.GET)
    public void editContactTset(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/create_contact_page.jsp").forward(req, resp);
    }


    private static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }


    private String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

    @RequestMapping(uri = "/dowloadattachment", method = RequestMapping.Method.GET)
    public void dowloadAttachment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("createContactDTO");
        int id = Integer.parseInt(req.getParameter("id"));
        for (AttachmentDTO attachmentDTO : contactDTO.getAttachmentDTOs()) {
            if (attachmentDTO.getId() == id) {
                File my_file = new File(attachmentDTO.getPathString());
                resp.setHeader("Content-disposition", "attachment; filename=" + attachmentDTO.getNameString() + "." + getFileExtension(my_file));
                OutputStream out = resp.getOutputStream();
                FileInputStream in = new FileInputStream(my_file);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.flush();
                break;
            }
        }

    }
}
