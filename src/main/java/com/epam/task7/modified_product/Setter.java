package com.epam.task7.modified_product;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Oleksii Kushch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD})
public @interface Setter {
    String FIELD_NAME();
}
