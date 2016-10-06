package main.java.MVC;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface HandlerMapping {
    Handler getHandler (HttpServletRequest request);
    HandlerMapping addControllerClass(Class controllerClass) throws IllegalAccessException, InstantiationException;
    public Map<HandlerMappingImpl.Entry<String, RequestMapping.Method>, Handler> getHandlerMap();
}
