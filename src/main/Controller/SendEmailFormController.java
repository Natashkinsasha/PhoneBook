package main.Controller;


import main.DTO.ContactDTO;
import main.DTO.EmailDTO;
import main.Email.Sender;
import main.MVC.RequestMapping;
import main.Servic.ContactService;
import main.Servic.ContactServiceImpl;
import main.Servic.ServiceException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

public class SendEmailFormController {
    @RequestMapping(uri = "/sendemail", method = RequestMapping.Method.GET)
    public void openEmailForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmailDTO emailDTO = new EmailDTO();
        req.getSession().setAttribute("emailDTO",emailDTO);
        if (req.getParameter("id")!=null) {
            ContactService contactService = new ContactServiceImpl();
            ContactDTO contactDTO = null;
            try {
                contactDTO = contactService.getContactById(Integer.valueOf(req.getParameter("id")));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            emailDTO.setWhom(contactDTO.getEmailString());
            //emailDTO.setText(generateText(contactDTO, req));
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/send_email_form.jsp").forward(req, resp);
    }

    private String generateText(ContactDTO contactDTO, HttpServletRequest req){
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        String path =req.getSession().getServletContext().getRealPath("resourses\\template.vm");
        Template t = ve.getTemplate(path);
        VelocityContext context = new VelocityContext();
        context.put("firstname", contactDTO.getFirstName());
        context.put("secondname", contactDTO.getSecondNameString());
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        return writer.toString();
    }

    @RequestMapping(uri = "/sendemail", method = RequestMapping.Method.POST)
    public void sendEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String whom = req.getParameter("whom");
        String them = req.getParameter("theme");
        String text = req.getParameter("text");
        Sender tlsSender = new Sender("natashkinsasha@gmail.com", "Natashkinsasha6426384");
        tlsSender.send(them, text, "natashkinsasha@gmail.com", whom);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/send_email_form.jsp").forward(req, resp);

    }

}
