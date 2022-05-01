package com.epam.task7.create_product.strategy;

import com.epam.task1.entity.Commodity;
import com.epam.task4.MainApp;
import com.epam.task6.create_product.CreateProduct;
import com.epam.task6.util.ProductDataConsoleScanner;
import com.epam.task7.create_product.ProductField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creating and initialization new product via console with using reflection tech
 * @author Oleksii Kushch
 */
public class ReflectManualCreateProduct implements CreateProduct {
    private final Commodity commodity;

    public ReflectManualCreateProduct(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public Commodity create() {
        List<Field> fieldList = new ArrayList<>();
        CreateProduct.getProductFields(fieldList, commodity.getClass());

        ProductDataConsoleScanner productDataConsoleScanner = MainApp.getContext().getProductDataConsoleScanner();
        for (Field field : fieldList) {
            try {
                field.setAccessible(true);
                field.set(commodity,
                        productDataConsoleScanner.getMethodContainer()
                                .get(field.getAnnotation(ProductField.class).KEY()).get());
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }

        return commodity;
    }
}
