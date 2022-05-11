package com.epam.task7.create_product;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Oleksii Kushch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD})
public @interface ProductField {
    /**
     * @return string key that matches the specified field
     */
    String KEY();
}
