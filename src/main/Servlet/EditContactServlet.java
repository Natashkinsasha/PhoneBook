package main.Servlet;


import main.DTO.ContactDTO;
import main.Servic.ContactService;
import main.Servic.ContactServiceImpl;
import main.Servic.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editcontact")
public class EditContactServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContactService contactService = new ContactServiceImpl();
        ContactDTO contactDTO = null;
        try {
            contactDTO = contactService.getContactById(Integer.valueOf(req.getParameter("id")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("createContactDTO", contactDTO);
        resp.sendRedirect("/createcontact");
    }
}
