package com.epam.task6.create_product;

import com.epam.task1.entity.Commodity;
import com.epam.task7.create_product.ProductField;

import java.lang.reflect.Field;
import java.util.List;

public interface CreateProduct {
    Commodity create();

    static void getProductFields(List<Field> fieldList, Class<?> clazz) {
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
