package by.itechart.phonebook.MVC;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public interface Handler {
    void execute() throws  Exception;
    public Handler setRequest(HttpServletRequest request);
    public Handler setResponce(HttpServletResponse response);
}
