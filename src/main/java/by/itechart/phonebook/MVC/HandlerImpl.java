package by.itechart.phonebook.MVC;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerImpl implements Handler {
    private final static Logger log = Logger.getLogger(HandlerImpl.class);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Method method;
    private Object controller;
    private String pathErrorPage;

    public HandlerImpl(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    @Override
    public void execute() throws Exception {
        method.invoke(controller, request, response);
    }


    @Override
    public Handler setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public Handler setResponce(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    public Handler setPathErrorPage(String pathErrorPage) {
        this.pathErrorPage = pathErrorPage;
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
