package main.Servlet;



import main.SQLGenerator.StringQuere;
import main.Servic.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/deletecontact")
public class DeleteContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactService contactService = new ContactServiceImpl();
        try {
            contactService.deleteContact(Integer.valueOf(req.getParameter("id")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactService contactService = new ContactServiceImpl();
        for (Enumeration<String> parametrs = req.getParameterNames(); parametrs.hasMoreElements();){
            try {
                String id = parametrs.nextElement();
                contactService.deleteContact(Integer.valueOf(id));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/");
    }
}
