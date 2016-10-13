package by.itechart.phonebook.Validator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Validator {

    public BindingResult check(Object object) {
        BindingResult bindingResult = new BindingResult();
        Class<?> objectClass = object.getClass();
        Field[] declaredFields = objectClass.getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(NotNull.class) && field.get(object) == null) {
                    bindingResult.addError(field.getName(), new NullPointerException());
                }

                if (field.isAnnotationPresent(Pattern.class) && field.getType() == String.class && field.get(object) != null) {
                    Pattern annotation = field.getAnnotation(Pattern.class);
                    String pattern = annotation.value();
                    if (!java.util.regex.Pattern.compile(pattern).matcher((String) field.get(object)).matches()) {
                        bindingResult.addError(field.getName(), new PatternMismatchException());
                    }
                }
                if (field.isAnnotationPresent(MaxSize.class) && field.get(object) != null && field.getType() == String.class) {
                    MaxSize annotation = field.getAnnotation(MaxSize.class);
                    String string = (String) field.get(object);
                    if (string.length() > annotation.value()) {
                        bindingResult.addError(field.getName(), new BigStrngException());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bindingResult;
    }

    public class BindingResult {
        Map<String, Throwable> map;

        public BindingResult() {
            map = new HashMap();
        }

        BindingResult addError(String field, Throwable throwable) {
            map.put(field, throwable);
            return this;
        }

        public boolean hasErroe() {
            if (map.size() == 0) {
                return false;
            } else {
                return true;
            }
        }

    }

}
