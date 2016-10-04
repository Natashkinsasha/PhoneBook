package main.Controller;


import main.MVC.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SerchFormController {
    @RequestMapping(uri = "/serchcontact", method = RequestMapping.Method.GET)
    public String serchForm(HttpServletRequest req, HttpServletResponse resp){
        return "/WEB-INF/jsp/pages/serch_form.jsp";
    }
}
