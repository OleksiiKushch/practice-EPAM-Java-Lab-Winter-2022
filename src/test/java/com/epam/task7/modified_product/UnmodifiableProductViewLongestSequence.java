package com.epam.task7.modified_product;

import com.epam.task1.entity.Commodity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnmodifiableProductViewLongestSequence {

    @Test
    void getInstance() {
        BaseItem unmodifiableProduct = UnmodifiableProduct.getInstance(
                new Commodity(1L, "Commodity1", new BigDecimal("10.25"), 10));

        assertEquals(1L, unmodifiableProduct.getId());
        assertEquals("Commodity1", unmodifiableProduct.getFrontTitle());
        assertEquals(new BigDecimal("10.25"), unmodifiableProduct.getPrice());
        assertEquals(10, unmodifiableProduct.getAmount());

        assertThrows(UnsupportedOperationException.class, () -> unmodifiableProduct.setId(2L));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableProduct.setFrontTitle("Commodity2"));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableProduct.setPrice(new BigDecimal("0")));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableProduct.setAmount(0));
    }
}