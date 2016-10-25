package by.itechart.phonebook.MVC;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandlerMappingImpl implements HandlerMapping {
    private final static Logger log = Logger.getLogger(HandlerMappingImpl.class);
    Map<Entry<String, RequestMapping.Method>, Handler> uriHandlerMap;
    Map<Class<? extends Throwable>, Handler> exceptionHandlerMap;
    String pathErrorPage;


    public HandlerMappingImpl() {
        uriHandlerMap = new HashMap<>();
        exceptionHandlerMap = new HashMap<>();
    }
    @Override
    public Map<Entry<String, RequestMapping.Method>, Handler> getUriHandlerMap() {
        return uriHandlerMap;
    }

    public void setPathErrorPage(String pathErrorPage) {
        this.pathErrorPage = pathErrorPage;
    }

    @Override
    public Handler getHandler(HttpServletRequest request) throws IncorrectURI {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Handler handler = uriHandlerMap.get(Entry.e(uri, RequestMapping.Method.valueOf(method)));
        if (handler == null) {
            throw new IncorrectURI(request.getRequestURI()+" don't find");
        }
        return handler;
    }

    @Override
    public Handler getHandler(Class<? extends Throwable> exceptionClass) throws ExceptionNotHandler{
        Handler handler = exceptionHandlerMap.get(exceptionClass);
        if (handler == null) {
            throw new ExceptionNotHandler("Handler for "+exceptionClass+" don't find");
        }
        return handler;
    }


    @Override
    public HandlerMapping addControllerClass(Class aClass) {
        Object controller = null;
        try {
            controller = aClass.newInstance();
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                String value = annotation.uri();
                RequestMapping.Method method1 = annotation.method();
                uriHandlerMap.put(Entry.e(value, method1), new HandlerImpl(controller, method).setPathErrorPage(pathErrorPage));
            }
            if (method.isAnnotationPresent(ExceptionHandler.class)) {
                ExceptionHandler annotation = method.getAnnotation(ExceptionHandler.class);
                Class<? extends Throwable> exceptionClass = annotation.value();
                exceptionHandlerMap.put(exceptionClass, new HandlerImpl(controller, method));
            }
        }
        return this;
    }

    static class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {

            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        static <K, V> Entry<K, V> e(K key, V value) {
            return new Entry<K, V>(key, value);
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;

            Entry<?, ?> e = (Entry<?, ?>) obj;
            return key == e.key || (key != null && key.equals(e.key))
                    && value == e.value || (value != null && value.equals(e.value));
        }

        @Override
        public int hashCode() {

            return Arrays.asList(key, value).hashCode();
        }

        @Override
        public String toString() {

            return Arrays.asList(key, value).toString();
        }
    }

}
