package by.itechart.phonebook.MVC;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerImpl implements Handler{
    private final static Logger log =Logger.getLogger(HandlerImpl.class);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Method method;
    private Object controller;

    public HandlerImpl(Object controller, Method method){
        this.controller=controller;
        this.method=method;
    }

    @Override
    public void execute() {
        try {
            method.invoke(controller, request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            log.error(e);
        }
    }


    @Override
    public Handler setRequest(HttpServletRequest request) {
        this.request=request;
        return this;
    }

    @Override
    public Handler setResponce(HttpServletResponse response) {
        this.response=response;
        return this;
    }

    @Override
    public String toString() {
        return "HandlerImpl{" +
                "request=" + request +
                ", response=" + response +
                ", method=" + method +
                ", controller=" + controller +
                '}';
    }
}
