package com.epam.task7.create_product;

import com.epam.task6.create_product.CreateProduct;

import java.lang.reflect.Field;
import java.util.List;

public interface ReflectCreateProduct extends CreateProduct {
    default void getProductFields(List<Field> fieldList, Class<?> clazz) {
        if (clazz.getSuperclass() != Object.class) {
            getProductFields(fieldList, clazz.getSuperclass());
        }
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            if (field.isAnnotationPresent(ProductField.class)) {
                fieldList.add(field);
            }
        }
    }
}
