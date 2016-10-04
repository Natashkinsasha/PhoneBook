package main.MVC;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandlerMappingImpl implements HandlerMapping {
    Map<Entry<String, RequestMapping.Method>, Handler> handlerMap;

    public HandlerMappingImpl() {
        handlerMap = new HashMap<>();
    }

    public Map<Entry<String, RequestMapping.Method>, Handler> getHandlerMap() {
        return handlerMap;
    }

    @Override
    public Handler getHandler(HttpServletRequest request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Handler handler = handlerMap.get(Entry.e(uri, RequestMapping.Method.valueOf(method)));
        return handler;
    }



    public HandlerMapping addControllerClass(Class aClass) throws IllegalAccessException, InstantiationException {
        Object controller = aClass.newInstance();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(RequestMapping.class)){
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                String value = annotation.uri();
                RequestMapping.Method method1 = annotation.method();
                handlerMap.put(Entry.e(value,method1), new Handler() {
                    HttpServletRequest request;
                    HttpServletResponse response;
                    @Override
                    public String execute() {
                        try {
                            return (String) method.invoke(controller, request, response);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return null;
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
                });
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
            return new Entry<K,V>(key,value);
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == this) return true;
            if (obj == null || obj.getClass() != getClass()) return false;

            Entry<?, ?> e = (Entry<?,?>) obj;
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
