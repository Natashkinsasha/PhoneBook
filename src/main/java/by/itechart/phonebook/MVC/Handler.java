package by.itechart.phonebook.MVC;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
    void execute();
    public Handler setRequest(HttpServletRequest request);
    public Handler setResponce(HttpServletResponse response);
}
