package by.itechart.phonebook.MVC;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    void handle(HttpServletRequest request, HttpServletResponse response, Handler handler);
}
