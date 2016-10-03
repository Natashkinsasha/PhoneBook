package main.MVC;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    String handle(HttpServletRequest request, HttpServletResponse response, Handler handler);
}
