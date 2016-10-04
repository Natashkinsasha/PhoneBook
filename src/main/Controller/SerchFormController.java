package main.Controller;


import main.MVC.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SerchFormController {
    @RequestMapping(uri = "/serchcontact", method = RequestMapping.Method.GET)
    public void serchForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/serch_form.jsp").forward(req, resp);
    }
}
