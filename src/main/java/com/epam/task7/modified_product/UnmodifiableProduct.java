package com.epam.task7.modified_product;

import java.lang.reflect.Proxy;

public class UnmodifiableProduct {
    private static final String MSG_EXCEPTION = "Product can't be modification!";

    /**
     * Factory method for creating a non-modifiable representation of a product object ({@link BaseItem})
     * @param product base product
     * @return non-modifiable product
     */
    public static BaseItem getInstance(BaseItem product) {
        return (BaseItem) Proxy.newProxyInstance(
                UnmodifiableProduct.class.getClassLoader(),
                new Class[] { BaseItem.class }, (proxy, method, methodArgs) -> {
                    if (method.isAnnotationPresent(Setter.class)) {
                        throw new UnsupportedOperationException(MSG_EXCEPTION);
                    } else {
                        return method.invoke(product, methodArgs);
                    }
                });
    }
}
