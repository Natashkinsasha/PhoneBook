package main.Controller;


import main.DTO.ContactDTO;
import main.DTO.EmailDTO;
import main.MVC.RequestMapping;
import main.Servic.ContactService;
import main.Servic.ContactServiceImpl;
import main.Servic.ServiceException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.List;

public class SendEmailFormController {
    @RequestMapping(uri = "/sendemail", method = RequestMapping.Method.GET)
    public String sendOneEmail(HttpServletRequest req, HttpServletResponse resp) {
        EmailDTO emailDTO = new EmailDTO();
        ContactService contactService = new ContactServiceImpl();
        ContactDTO contactDTO = null;
        try {
            contactDTO = contactService.getContactById(Integer.valueOf(req.getParameter("id")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        emailDTO.setWhom(contactDTO.getMaleString());
        req.getSession().setAttribute("EmailDTO", emailDTO);
        req.getSession().setAttribute("EmailContactDTO", contactDTO);
        return "/WEB-INF/jsp/pages/send_email_form.jsp";
    }


    @RequestMapping(uri = "/sendesomemail", method = RequestMapping.Method.POST)
    public String sendSomeEmail(HttpServletRequest req, HttpServletResponse resp) {
        EmailDTO emailDTO = new EmailDTO();
        StringBuffer whom = new StringBuffer();
        ContactService contactService = new ContactServiceImpl();
        for (Enumeration<String> parametrs = req.getParameterNames(); parametrs.hasMoreElements(); ) {
            try {
                String id = parametrs.nextElement();
                whom = whom.append(contactService.getContactById(Integer.valueOf(id)).getEmailString()).append(" ");
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        emailDTO.setWhom(whom.toString());
        req.getSession().setAttribute("EmailDTO", emailDTO);
        return "/WEB-INF/jsp/pages/send_email_form.jsp";
    }

    @RequestMapping(uri = "/choosetemplate", method = RequestMapping.Method.GET)
    public String chooseTempllate(HttpServletRequest req, HttpServletResponse resp) {
        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("EmailContactDTO");
        EmailDTO emailDTO = (EmailDTO) req.getSession().getAttribute("EmailDTO");
        Velocity.init();
        VelocityContext vc = new VelocityContext();
        vc.put("firstname", contactDTO.getFirstName());
        vc.put("secondname", contactDTO.getSecondName());
        String path =req.getServletContext().getRealPath("")+"META-INF\\mail_template\\first_template.vm";
        Template template = Velocity.getTemplate(path, "utf-8");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringWriter stringWriter = new StringWriter();
        template.merge(vc, stringWriter);
        String text = stringWriter.toString();
        try {
            stringWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        emailDTO.setText(text);
        return "/WEB-INF/jsp/pages/send_email_form.jsp";
    }
}
