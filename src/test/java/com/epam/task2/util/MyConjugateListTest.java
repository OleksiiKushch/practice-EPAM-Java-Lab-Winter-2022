package com.epam.task2.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MyConjugateListTest {
    private MyConjugateList<Integer> list;

    @BeforeEach
    void setUp() {
        List<Integer> testList1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> testList2 = new ArrayList<>(Arrays.asList(4, 5, 6));
        list = new MyConjugateList<>(testList1, testList2);
    }

    @Test
    void size() {
        assertEquals(6, list.size());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
        MyConjugateList<Integer> testList1 = new MyConjugateList<>(new ArrayList<>(), new ArrayList<>());
        assertTrue(testList1.isEmpty());
    }

    @Test
    void iterator() {
        Iterator<Integer> iterator = list.iterator();
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(6, numberOfIterations);
    }

    @Test
    void iterator_ifUnmodifiablePartIsEmpty() {
        MyConjugateList<Integer> testList = new MyConjugateList<>(
                new ArrayList<>(), new ArrayList<>(Arrays.asList(4, 5, 6)));
        Iterator<Integer> iterator = testList.iterator();
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(3, numberOfIterations);
    }

    @Test
    void iterator_ifModifiablePartIsEmpty() {
        MyConjugateList<Integer> testList = new MyConjugateList<>(
                new ArrayList<>(Arrays.asList(1, 2, 3)), new ArrayList<>());
        Iterator<Integer> iterator = testList.iterator();
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(3, numberOfIterations);
    }

    @Test
    void iterator_ifBothPartsIsEmpty() {
        MyConjugateList<Integer> testList = new MyConjugateList<>(
                new ArrayList<>(), new ArrayList<>());
        Iterator<Integer> iterator = testList.iterator();
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(0, numberOfIterations);
    }

    @Test
    void iterator_ifPartCollectionsFromDifferentClasses () {
        MyConjugateList<Integer> testList = new MyConjugateList<>(
                new LinkedList<>(Arrays.asList(1, 2, 3)), new HashSet<>(Arrays.asList(4, 5, 6)));
        Iterator<Integer> iterator = testList.iterator();
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(6, numberOfIterations);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 3, 5})
    void get(int index) {
        assertEquals(index + 1, list.get(index));
    }

    @Test
    void get_shouldThrowIndexOutOfBoundsException() {
        Exception exception1 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        Exception exception2 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(6));

        assertEquals("Index: -1, Size: 6", exception1.getMessage());
        assertEquals("Index: 6, Size: 6", exception2.getMessage());
    }

    @Test
    void set() {
        assertEquals(4, list.set(3, 99));
        assertEquals(99, list.get(3));
    }

    @Test
    void set_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> list.set(0, 99));
    }

    @Test
    void add() {
        list.add(3, 99);
        assertEquals(99, list.get(3));
        assertEquals(4, list.get(4));
        assertEquals(6, list.get(6));

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(2));

        list.add(7, 99);
        assertEquals(99, list.get(7));
    }

    @Test
    void add_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> list.add(0, 99));
    }

    @Test
    void remove() {
        assertEquals(4, list.remove(3));
        assertEquals(5, list.get(3));
        assertEquals(6, list.get(4));

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(2));

        assertEquals(6, list.remove(4));
    }

    @Test
    void remove_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
    }
}