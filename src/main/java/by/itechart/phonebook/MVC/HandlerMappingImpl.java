package by.itechart.phonebook.MVC;


import by.itechart.phonebook.MVC.HandlerMapping;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandlerMappingImpl implements HandlerMapping {
    private final static Logger log = Logger.getLogger(HandlerMappingImpl.class);
    Map<Entry<String, RequestMapping.Method>, Handler> handlerMap;
    String pathErrorPage;


    public HandlerMappingImpl() {
        handlerMap = new HashMap<>();
    }

    public Map<Entry<String, RequestMapping.Method>, Handler> getHandlerMap() {
        return handlerMap;
    }

    public void setPathErrorPage(String pathErrorPage) {
        this.pathErrorPage = pathErrorPage;
    }

    @Override
    public Handler getHandler(HttpServletRequest request) throws URIIncorrect {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Handler handler = handlerMap.get(Entry.e(uri, RequestMapping.Method.valueOf(method)));
        if (handler == null) {
            throw new URIIncorrect(request.getRequestURI()+" don't find");
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
                handlerMap.put(Entry.e(value, method1), new HandlerImpl(controller, method).setPathErrorPage(pathErrorPage));
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
