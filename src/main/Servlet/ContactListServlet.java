package main.Servlet;


import main.DTO.ContactDTO;
import main.Servic.MainTableService;
import main.Servic.MainTableServiceImpl;
import main.Servic.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class ContactListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainTableService mainTableService = new MainTableServiceImpl();
        String page = req.getParameter("page");
        HttpSession session = req.getSession();
        if (page != null) {
            session.setAttribute("page", Integer.valueOf(page));
        }
        List<ContactDTO> contactDTOs = null;
        try {
            contactDTOs = mainTableService.getSerchSortLimitContacts((ContactDTO) req.getSession().getAttribute("serchPattern"), (Integer) req.getSession().getAttribute("page"), null);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("DTOs",contactDTOs);
        int pageCol = 0;
        try {
            pageCol = mainTableService.getCountTablePage((ContactDTO) req.getSession().getAttribute("serchPattern"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        session.setAttribute("pageCol",pageCol);
        req.getRequestDispatcher("WEB-INF/jsp/pages/contact_list.jsp").forward(req, resp);
    }


}
