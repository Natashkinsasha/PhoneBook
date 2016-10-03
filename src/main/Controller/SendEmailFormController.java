package main.Controller;


import main.DTO.ContactDTO;
import main.DTO.TelephoneDTO;
import main.MVC.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendEmailFormController {
    @RequestMapping(value = "/page/sendemail", method = RequestMapping.Method.GET)
    public String updateTelephone(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/jsp/pages/send_email_form.jsp";
    }
}
