package by.itechart.phonebook.Controller;

import by.itechart.phonebook.DTO.ContactDTO;
import by.itechart.phonebook.DTO.EmailDTO;
import by.itechart.phonebook.DTO.EmailTemplateDTO;
import by.itechart.phonebook.Email.Sender;
import by.itechart.phonebook.MVC.RequestMapping;
import by.itechart.phonebook.Repository.EmailTemplateRepository;
import by.itechart.phonebook.Repository.EmailTemplateRepositoryImpl;
import by.itechart.phonebook.Repository.RepositoryException;
import by.itechart.phonebook.Servic.ContactService;
import by.itechart.phonebook.Servic.ContactServiceImpl;
import by.itechart.phonebook.Servic.ServiceException;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

public class SendEmailFormController {
    private final static Logger log =Logger.getLogger(SendEmailFormController.class);
    @RequestMapping(uri = "/sendemail", method = RequestMapping.Method.GET)
    public void openEmailForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmailTemplateDTO(new EmailTemplateDTO());
        req.getSession().setAttribute("emailDTO",emailDTO);
        EmailTemplateRepository emailTemplateRepository = new EmailTemplateRepositoryImpl();
        List<EmailTemplateDTO> emailTemplateDTOList=null;
        try {
            emailTemplateDTOList = emailTemplateRepository.toList();
        } catch (RepositoryException e) {
            e.printStackTrace();
            log.error(e);
        }
        req.getSession().setAttribute("templates",emailTemplateDTOList);


        ContactDTO contactDTO = (ContactDTO) req.getSession().getAttribute("emailContactDTO");
        if (req.getParameter("id")!=null) {
            ContactService contactService = new ContactServiceImpl();
            try {
                contactDTO = contactService.getContactById(Integer.valueOf(req.getParameter("id")));
            } catch (ServiceException e) {
                e.printStackTrace();
                log.error(e);
            }

            req.getSession().setAttribute("emailContactDTO",contactDTO);
        }
        if (req.getSession().getAttribute("emailContactDTO")==null){
            contactDTO=new ContactDTO();
        }


        emailDTO.setWhom(contactDTO.getEmailString());

        EmailTemplateDTO emailTemplateDTO = null;
        if (req.getParameter("id_template")!=null){
            try {
                emailTemplateDTO= emailTemplateRepository.get(Integer.valueOf(req.getParameter("id_template")));
            } catch (RepositoryException e) {
                e.printStackTrace();
                log.error(e);
            }
            emailDTO.setText(generateText(contactDTO,emailTemplateDTO));
            emailDTO.setEmailTemplateDTO(emailTemplateDTO);

        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/send_email_page.jsp").forward(req, resp);
    }

    private String generateText(ContactDTO contactDTO, EmailTemplateDTO emailTemplate){
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(emailTemplate.getPathString(), "UTF-8");
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
        Sender tlsSender = new Sender("natashkinsasha@gmail.com", "Natashkinsasha6426384");
        tlsSender.send(them, text, "natashkinsasha@gmail.com", whom);
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/send_email_page.jsp").forward(req, resp);
    }

}
