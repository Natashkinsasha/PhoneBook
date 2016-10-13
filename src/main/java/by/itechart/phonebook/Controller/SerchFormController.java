package by.itechart.phonebook.Controller;


import by.itechart.phonebook.MVC.RequestMapping;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SerchFormController {
    private final static Logger log =Logger.getLogger(SerchFormController.class);
    @RequestMapping(uri = "/serchcontact", method = RequestMapping.Method.GET)
    public void serchForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/serch_form_page.jsp").forward(req, resp);
    }
}
