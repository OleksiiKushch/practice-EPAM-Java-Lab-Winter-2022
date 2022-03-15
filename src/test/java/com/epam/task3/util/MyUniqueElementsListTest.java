package com.epam.task3.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyUniqueElementsListTest {

    private List<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyUniqueElementsList<>(Arrays.asList(1, 2, 3));
    }

    @Test
    void constructorWithParamCollection_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new MyUniqueElementsList<>(Arrays.asList(1, 2, 2)));

        assertThrows(NullPointerException.class, () -> new MyUniqueElementsList<>(null));
    }

    @Test
    void set() {
        assertEquals(3, list.set(2, 4));
        assertEquals(1, list.set(0, 1));

        list.add(null);
        assertNull(list.set(3, null));
    }

    @Test
    void set_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.set(2, 2));

        list.add(null);
        assertThrows(IllegalArgumentException.class, () -> list.set(0, null));

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, 5));
    }

    @Test
    void add() {
        assertTrue(list.add(4));
        assertEquals("[1, 2, 3, 4]", list.toString());
    }

    @Test
    void add_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.add(1));
    }

    @Test
    void add_ifElementIsNull_shouldThrowException() {
        list.add(null);
        assertThrows(IllegalArgumentException.class, () -> list.add(null));
    }

    @Test
    void addWithIndexParameter() {
        list.add(0, 4);
        assertEquals("[4, 1, 2, 3]", list.toString());
    }

    @Test
    void addWithIndexParameter_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.add(0, 1));
    }

    @Test
    void addAll() {
        assertTrue(list.addAll(Arrays.asList(4, 5)));
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
        assertFalse(list.addAll(new ArrayList<>()));
    }

    @Test
    void addAll_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.addAll(Arrays.asList(4, 1)));
        assertTrue(list.addAll(Arrays.asList(99, null)));
        assertThrows(IllegalArgumentException.class, () -> list.addAll(Arrays.asList(100, null)));

        assertThrows(IllegalArgumentException.class, () -> list.addAll(Arrays.asList(4, 5, 5)));
        assertThrows(IllegalArgumentException.class, () -> list.addAll(Arrays.asList(4, null, null)));

        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    void addAllWithIndexParameter() {
        assertTrue(list.addAll(0, Arrays.asList(4, 5)));
        assertEquals("[4, 5, 1, 2, 3]", list.toString());
        assertFalse(list.addAll(0, new ArrayList<>()));
    }

    @Test
    void addAllWithIndexParameter_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.addAll(0, Arrays.asList(4, 1)));
        assertTrue(list.addAll(0, Arrays.asList(99, null)));
        assertThrows(IllegalArgumentException.class, () -> list.addAll(0, Arrays.asList(100, null)));

        assertThrows(NullPointerException.class, () -> list.addAll(0, null));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(-1, new ArrayList<>()));
    }

    @Test
    public void replaceAll() {
        list.replaceAll(element -> element * 2);
        assertEquals("[2, 4, 6]", list.toString());
    }

    @Test
    public void replaceAll_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.replaceAll(element -> element / 2));

        assertThrows(NullPointerException.class, () -> list.replaceAll(null));
    }

    @Test
    void isUniqueElementsCollection() {
        assertTrue(MyUniqueElementsList.isUniqueElementsCollection(Arrays.asList(1, 2, 3)));
        assertFalse(MyUniqueElementsList.isUniqueElementsCollection(Arrays.asList(1, 2, 2)));
        assertFalse(MyUniqueElementsList.isUniqueElementsCollection(Arrays.asList(null, 2, null)));
    }

    @Test
    void isUniqueElementsCollection_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> MyUniqueElementsList.isUniqueElementsCollection(null));
    }
}