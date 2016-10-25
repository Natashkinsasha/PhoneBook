package by.itechart.phonebook.MVC;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface HandlerMapping {
    Handler getHandler(HttpServletRequest request) throws IncorrectURI;

    HandlerMapping addControllerClass(Class controllerClass);

    Handler getHandler(Class<? extends Throwable> exceptionClass) throws ExceptionNotHandler;

    Map<HandlerMappingImpl.Entry<String, RequestMapping.Method>, Handler> getUriHandlerMap();
}