package by.itechart.phonebook.MVC;


import by.itechart.phonebook.Controller.*;
import by.itechart.phonebook.Servlet.AutoSenderEmail;
import org.apache.commons.fileupload.FileUploadException;
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
    private HandlerMapping handlerMapping;
    private HandlerAdapter handlerAdapter;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        HandlerMapping handlerMapping = new HandlerMappingImpl();
        handlerMapping.addControllerClass(ContactsFormController.class).addControllerClass(CreateContactFormController.class).addControllerClass(SendEmailFormController.class).addControllerClass(SerchFormController.class).addControllerClass(ContactController.class).addControllerClass(ExceptionHandlerController.class);
        servletContext.setAttribute("HandlerMapping", handlerMapping);
        servletContext.setAttribute("HandlerAdapter", new HandlerAdapterImpl());
        servletContext.setAttribute("FormatterRequest", new FormatterRequest());
        log.info(handlerMapping.getUriHandlerMap().toString());
        try {
            AutoSenderEmail autoSenderEmail = new AutoSenderEmail();
            autoSenderEmail.start();
        } catch (Exception e) {
            log.error("Ошибка инициализации автоотправки email.", e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        log.info("uri " + req.getRequestURI() + " method " + req.getMethod());
        handlerMapping = (HandlerMapping) getServletContext().getAttribute("HandlerMapping");
        handlerAdapter = (HandlerAdapter) getServletContext().getAttribute("HandlerAdapter");
        Handler handler = null;
        try {
            formatRequest(req);
        } catch (FileUploadException e) {
            log.error(e);
        }
        try {
            handler = handlerMapping.getHandler(req);
            log.info(handler);
            try {
                handlerAdapter.handle(req, resp, handler);
            } catch (Exception e) {
                log.error(e.getCause());
                showErrorPage(e, req, resp);
            }
        } catch (IncorrectURI uriIncorrect) {
            log.error(uriIncorrect);
            showErrorPage(uriIncorrect, req, resp);
        }


    }

    private void showErrorPage(Exception e, HttpServletRequest req, HttpServletResponse resp) {
        try {
            Handler handler = handlerMapping.getHandler(e.getClass());
            try {
                handlerAdapter.handle(req, resp, handler);
            } catch (Exception e1) {
                log.error(e1);
            }
        } catch (ExceptionNotHandler exceptionNotHandler) {
            log.error(exceptionNotHandler);
        }
    }

    private void formatRequest(HttpServletRequest request) throws FileUploadException {
        FormatterRequest formatterRequest = (FormatterRequest) getServletContext().getAttribute("FormatterRequest");
        formatterRequest.setRequest(request);
        formatterRequest.format();
        formatterRequest.writeToLogAndConsoleAllAttribute();
    }
}
