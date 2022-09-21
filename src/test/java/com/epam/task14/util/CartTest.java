package com.epam.task14.util;

import com.epam.task13.entity.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CartTest {

    @Test
    void testPut_ifFirstPut() {
        Product testProduct = Mockito.mock(Product.class);
        Cart cart = new Cart();
        cart.put(testProduct, 2);

        Assertions.assertEquals(1, cart.getContainer().size());
        Assertions.assertEquals(2, cart.getContainer().get(testProduct));
    }

    @Test
    void testPut_ifSeveralPuts() {
        Product testProduct1 = Mockito.mock(Product.class);
        Product testProduct2 = Mockito.mock(Product.class);
        Cart cart = new Cart();
        cart.put(testProduct1, 2);
        cart.put(testProduct2, 1);

        Assertions.assertEquals(2, cart.getContainer().size());
        Assertions.assertEquals(2, cart.getContainer().get(testProduct1));
        Assertions.assertEquals(1, cart.getContainer().get(testProduct2));
    }

    @Test
    void testPut_ifSeveralPutsSaveProduct() {
        Product testProduct = Mockito.mock(Product.class);
        Cart cart = new Cart();
        cart.put(testProduct, 2);
        cart.put(testProduct, 1);

        Assertions.assertEquals(1, cart.getContainer().size());
        Assertions.assertEquals(3, cart.getContainer().get(testProduct));
    }

    @Test
    void testPut_ifObjectChange() {
        Product testProduct1 = Mockito.mock(Product.class);
        testProduct1.setId(1);
        Product testProduct2 = Mockito.mock(Product.class);
        testProduct2.setId(1);
        Cart cart = new Cart();

        cart.put(testProduct1, 2);
        cart.put(testProduct2, 1);

        Assertions.assertEquals(1, cart.getContainer().size());
        Assertions.assertEquals(3, cart.getContainer().get(testProduct2));
    }

    @Test
    void testRemove() {
        Product testProduct = Mockito.mock(Product.class);
        Cart cart = new Cart();
        cart.put(testProduct, 2);
        cart.remove(testProduct);

        Assertions.assertEquals(0, cart.getContainer().size());
    }

    @Test
    void testRemove_ifProductNotExists() {
        Product testProduct = Mockito.mock(Product.class);
        Cart cart = new Cart();
        cart.remove(testProduct);

        Assertions.assertEquals(0, cart.getContainer().size());
    }

    @Test
    void testRemove_ifObjectChange() {
        Product testProduct1 = Mockito.mock(Product.class);
        testProduct1.setId(1);
        Product testProduct2 = Mockito.mock(Product.class);
        testProduct2.setId(1);
        Cart cart = new Cart();
        cart.put(testProduct1, 2);

        cart.remove(testProduct2);

        Assertions.assertEquals(0, cart.getContainer().size());
    }

    @Test
    void testChangeAmount_ifObjectChange() {
        Product testProduct1 = Mockito.mock(Product.class);
        testProduct1.setId(1);
        Product testProduct2 = Mockito.mock(Product.class);
        testProduct2.setId(1);
        Cart cart = new Cart();
        cart.put(testProduct1, 2);

        cart.changeAmount(testProduct2, 5);

        Assertions.assertEquals(1, cart.getContainer().size());
        Assertions.assertEquals(5, cart.getContainer().get(testProduct2));
    }
}