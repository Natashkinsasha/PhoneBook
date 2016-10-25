package by.itechart.phonebook.MVC;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerAdapterImpl implements HandlerAdapter{
    private final static Logger log =Logger.getLogger(HandlerImpl.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception {
        handler.setRequest(request).setResponce(response).execute();
    }
}
