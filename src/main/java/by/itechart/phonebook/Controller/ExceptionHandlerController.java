package by.itechart.phonebook.Controller;


import by.itechart.phonebook.DAO.DAOException;
import by.itechart.phonebook.MVC.ExceptionHandler;
import by.itechart.phonebook.MVC.IncorrectURI;
import by.itechart.phonebook.MVC.RequestMapping;
import by.itechart.phonebook.Servis.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public class ExceptionHandlerController {

    @RequestMapping(uri = "/error", method = RequestMapping.Method.GET)
    public void exceptionPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pages/error_page.jsp").forward(req, resp);
    }


    @ExceptionHandler(ServletException.class)
    public void handleServletException(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(500);
        req.getSession().setAttribute("error", "Error on the server!");
        resp.sendRedirect("/error");
    }

    @ExceptionHandler(ServiceException.class)
    public void handleServiceException(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(500);
        req.getSession().setAttribute("error", "Error on the server!");
        resp.sendRedirect("/error");
    }

    @ExceptionHandler(IOException.class)
    public void handleServiceIOExcepti(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(500);
        req.getSession().setAttribute("error", "Error on the server!");
        resp.sendRedirect("/error");
    }

    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(500);
        req.getSession().setAttribute("error", "Error on the server!");
        resp.sendRedirect("/error");
    }

    @ExceptionHandler(NumberFormatException.class)
    public void handleNumberFormatException(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(400);
        req.getSession().setAttribute("error", "Inlegal parametrs in requst!");
        resp.sendRedirect("/");
    }



    @ExceptionHandler(IncorrectURI.class)
    public void handleIncorrectURIException(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(400);
        req.getSession().setAttribute("error", "Incorrect URI in requst!");
        resp.sendRedirect("/");
    }



}
