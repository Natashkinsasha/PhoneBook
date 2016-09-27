package main.Servlet;


import main.Servic.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deletetelephone")
public class DeleteTelephoneServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TelephoneService telephoneService = new TelephoneServiceImpl();
        try {
            telephoneService.deleteTelephone(Integer.valueOf(req.getParameter("id")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/");
    }
}
