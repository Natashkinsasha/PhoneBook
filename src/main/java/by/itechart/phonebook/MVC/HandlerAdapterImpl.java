package by.itechart.phonebook.MVC;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerAdapterImpl implements HandlerAdapter{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Handler handler) {
        handler.setRequest(request).setResponce(response).execute();
    }
}
