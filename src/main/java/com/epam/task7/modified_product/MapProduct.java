package com.epam.task7.modified_product;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the product interface ({@link BaseItem}) based on the map ({@link Map})
 */
public class MapProduct implements InvocationHandler {
    private static final String METHOD_NAME_TO_STRING = "toString";

    private final Map<String, Object> container;

    public MapProduct() {
        container = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.isAnnotationPresent(Getter.class)) {
            return container.get(method.getAnnotation(Getter.class).FIELD_NAME());
        } else if (method.isAnnotationPresent(Setter.class)) {
            return container.put(method.getAnnotation(Setter.class).FIELD_NAME(), args[0]);
        } else if (METHOD_NAME_TO_STRING.equals(method.getName())) {
            return toString();

        }
        return null;
    }

    @Override
    public String toString() {
        return "Product" + container;
    }
}
