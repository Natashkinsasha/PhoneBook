package by.itechart.phonebook.MVC;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface HandlerMapping {
    Handler getHandler (HttpServletRequest request) throws URIIncorrect;
    HandlerMapping addControllerClass(Class controllerClass);
    public Map<HandlerMappingImpl.Entry<String, RequestMapping.Method>, Handler> getHandlerMap();
}
