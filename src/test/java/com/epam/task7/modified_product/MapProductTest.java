package com.epam.task7.modified_product;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MapProductTest {

    @Test
    public void testMapProduct() {
        BaseItem mapProduct = (BaseItem) Proxy.newProxyInstance(
                MapProductTest.class.getClassLoader(),
                new Class[] { BaseItem.class },
                new MapProduct());

        assertEquals("Product{}", mapProduct.toString());
        assertNull(mapProduct.getId());
        assertNull(mapProduct.getFrontTitle());
        assertNull(mapProduct.getPrice());
        assertNull(mapProduct.getAmount());

        mapProduct.setId(3L);
        assertEquals(3, mapProduct.getId());
        assertEquals("Product{id=3}", mapProduct.toString());
        mapProduct.setFrontTitle("Gen");
        mapProduct.setPrice(new BigDecimal("10"));
        mapProduct.setAmount(12);
        assertEquals("Gen", mapProduct.getFrontTitle());
        assertEquals(new BigDecimal("10"), mapProduct.getPrice());
        assertEquals(12, mapProduct.getAmount());
        assertEquals("Product{frontTitle=Gen, amount=12, price=10, id=3}", mapProduct.toString());
    }

}