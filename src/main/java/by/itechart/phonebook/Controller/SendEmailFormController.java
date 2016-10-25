package by.itechart.phonebook.Controller;

import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.DTO.EmailDTO;

import by.itechart.phonebook.DTO.EmailTemplateDTO;
import by.itechart.phonebook.Servis.SenderServiceImpl;
import by.itechart.phonebook.MVC.RequestMapping;

import by.itechart.phonebook.Servis.ContactService;
import by.itechart.phonebook.Servis.ContactServiceImpl;
import by.itechart.phonebook.Servis.ServiceException;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class SendEmailFormController {
    private final static Logger log = Logger.getLogger(SendEmailFormController.class);

    @RequestMapping(uri = "/sendemail", method = RequestMapping.Method.GET)
    public void openEmailForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {
        String id_template = req.getParameter("id_template");
        if (id_template == null) {
            req.getSession().removeAttribute("emailContactDTO");
        }
        ContactDTO contactDTO = null;
        if (req.getParameter("id") != null) {
            ContactService contactService = new ContactServiceImpl();
            try {
                contactDTO = contactService.getContactById(Integer.valueOf(req.getParameter("id")));
            } catch (ServiceException e) {
                e.printStackTrace();
                log.error(e);
            }
            req.getSession().setAttribute("emailContactDTO", contactDTO);
        }
        contactDTO = (ContactDTO) req.getSession().getAttribute("emailContactDTO");
        if (contactDTO == null) {
            contactDTO = new ContactDTO();
        }
        EmailDTO emailDTO = new EmailDTO();
        req.getSession().setAttribute("emailDTO", emailDTO);
        emailDTO.setWhom(contactDTO.getEmailString());
        List<EmailTemplateDTO> templates = new ArrayList<>();
        File folder = new File(this.getClass().getClassLoader().getResource("").getPath() + "\\templates");
        File[] files = folder.listFiles();
        int i = 0;
        for (File file : files) {
            String name = file.getName();
            templates.add(new EmailTemplateDTO().setId(i++).setName(name.replace(".vm", "")).setTemplatePath("templates\\" + name));
        }
        req.getSession().setAttribute("templates", templates);


        req.getSession().setAttribute("idChooseTemplate", id_template);
        if (id_template != null) {
            log.debug(id_template);
            int id = Integer.valueOf(id_template);
            EmailTemplateDTO emailTemplateDTO = templates.parallelStream().filter((e) -> e.getId() == id).findFirst().orElse(new EmailTemplateDTO());
            emailDTO.setText(generateText(contactDTO, emailTemplateDTO));
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/send_email_page.jsp").forward(req, resp);
    }

    private String generateText(ContactDTO contactDTO, EmailTemplateDTO emailTemplate) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(emailTemplate.getTemplatePath(), "UTF-8");
        StringWriter writer = new StringWriter();
        VelocityContext context = new VelocityContext();
        context.put("firstname", contactDTO.getFirstNameString());
        context.put("secondname", contactDTO.getSecondNameString());
        template.merge(context, writer);
        String text = writer.toString();
        return text;
    }


    @RequestMapping(uri = "/sendemail", method = RequestMapping.Method.POST)
    public void sendEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String whom = req.getParameter("whom");
        String them = req.getParameter("theme");
        String text = req.getParameter("text");
        SenderServiceImpl tlsSenderService = new SenderServiceImpl();
        tlsSenderService.send(them, text, "natashkinsasha@gmail.com", whom);
        req.getSession().setAttribute("success", "Letter has been sended!");
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/send_email_page.jsp").forward(req, resp);
    }

}
