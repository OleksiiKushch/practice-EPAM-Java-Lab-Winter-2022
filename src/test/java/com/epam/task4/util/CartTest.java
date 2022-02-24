package com.epam.task4.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void put() {
        Cart cart = Cart.getInstance();

        cart.put(1L, 2);
        assertEquals("{1=2}", cart.getContainer().toString());

        cart.put(2L, 1);
        assertEquals("{1=2, 2=1}", cart.getContainer().toString());

        cart.put(1L, 1);
        assertEquals("{1=3, 2=1}", cart.getContainer().toString());
    }
}