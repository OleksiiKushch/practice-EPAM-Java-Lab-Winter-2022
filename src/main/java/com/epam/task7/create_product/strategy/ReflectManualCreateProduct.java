package com.epam.task7.create_product.strategy;

import com.epam.task1.entity.Commodity;
import com.epam.task6.util.ProductDataConsoleScanner;
import com.epam.task7.create_product.ProductField;
import com.epam.task7.create_product.ReflectCreateProduct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creating and initialization new product via console with using reflection tech
 * @author Oleksii Kushch
 */
public class ReflectManualCreateProduct implements ReflectCreateProduct {
    private final Commodity commodity;
    private final ProductDataConsoleScanner productDataConsoleScanner;

    public ReflectManualCreateProduct(Commodity commodity, ProductDataConsoleScanner productDataConsoleScanner) {
        this.productDataConsoleScanner = productDataConsoleScanner;
        this.commodity = commodity;
    }

    @Override
    public Commodity create() {
        List<Field> fieldList = new ArrayList<>();
        getProductFields(fieldList, commodity.getClass());

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
