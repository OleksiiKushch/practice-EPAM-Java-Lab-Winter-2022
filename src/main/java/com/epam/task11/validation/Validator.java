package com.epam.task11.validation;

import java.util.List;

/**
 * @author Oleksii Kushch
 */
public interface Validator<T> {
    List<String> isValid(T t);
    default boolean isNullOrBlank(String param) {
        return param == null || param.isBlank();
    }
}
