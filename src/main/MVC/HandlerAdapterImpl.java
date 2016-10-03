package main.MVC;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerAdapterImpl implements HandlerAdapter{
    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, Handler handler) {
        return handler.setRequest(request).setResponce(response).execute();
    }
}
