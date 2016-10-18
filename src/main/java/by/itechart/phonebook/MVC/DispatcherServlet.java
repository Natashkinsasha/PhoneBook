package by.itechart.phonebook.MVC;


import by.itechart.phonebook.Controller.ContactsFormController;
import by.itechart.phonebook.Controller.CreateContactFormController;
import by.itechart.phonebook.Controller.SendEmailFormController;
import by.itechart.phonebook.Controller.SerchFormController;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(DispatcherServlet.class);

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        HandlerMapping handlerMapping = new HandlerMappingImpl();
        handlerMapping.addControllerClass(ContactsFormController.class).addControllerClass(CreateContactFormController.class).addControllerClass(SendEmailFormController.class).addControllerClass(SerchFormController.class);
        servletContext.setAttribute("HandlerMapping", handlerMapping);
        servletContext.setAttribute("HandlerAdapter", new HandlerAdapterImpl());
        log.info(handlerMapping.getHandlerMap().toString());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        log.info("uri " + req.getRequestURI() + " method " + req.getMethod());
        HandlerMapping handlerMapping = (HandlerMapping) getServletContext().getAttribute("HandlerMapping");
        HandlerAdapter handlerAdapter = (HandlerAdapter) getServletContext().getAttribute("HandlerAdapter");
        Handler handler = null;
        try {
            handler = handlerMapping.getHandler(req);
            log.info(handler);
            handlerAdapter.handle(req, resp, handler);
        } catch (URIIncorrect uriIncorrect) {
            log.error(uriIncorrect);
        }


    }
}
