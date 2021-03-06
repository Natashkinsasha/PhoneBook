package by.itechart.phonebook.MVC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    public enum Method {
        GET,
        POST;
    }

    String uri();
    Method method() default Method.GET;
}
