package main.Servlet;



import main.Servic.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteservlet")
public class DeleteContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactService contactService = new ContactServiceImpl();
        try {
            contactService.deleteContact(Integer.valueOf(req.getParameter("id")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/createcontact");
}
}
