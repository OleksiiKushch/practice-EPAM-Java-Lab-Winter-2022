package com.epam.task2.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyConjugateListTest {
    private MyConjugateList<Integer> list;

    @BeforeEach
    void setUp() {
        List<Integer> testList1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> testList2 = new ArrayList<>(Arrays.asList(4, 5, 6));
        list = new MyConjugateList<>(testList1, testList2);
    }

    @Test
    public void constructor_ifArgumentIsNull() {
        assertThrows(NullPointerException.class, () -> new MyConjugateList<>(
                null, new ArrayList<>(Arrays.asList(1, 2, 3))));
        assertThrows(NullPointerException.class, () -> new MyConjugateList<>(
                new ArrayList<>(Arrays.asList(1, 2, 3)), null));
        assertThrows(NullPointerException.class, () -> new MyConjugateList<>(null, null));
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
    void iterator_ifPartListsFromDifferentClasses () {
        MyConjugateList<Integer> testList = new MyConjugateList<>(
                new ArrayList<>(Arrays.asList(1, 2, 3)), new LinkedList<>(Arrays.asList(4, 5, 6)));
        Iterator<Integer> iterator = testList.iterator();
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(6, numberOfIterations);
    }

    @Test
    void toArray() {
        assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(list.toArray()));
    }

    @Test
    void toArrayWithArrayParameter() {
        assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(list.toArray(new Integer[] {7, 8, 9})));
        assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(list.toArray(new Integer[] {7, 8, 9, 10 , 11, 12})));
        assertEquals("[1, 2, 3, 4, 5, 6, null]", Arrays.toString(list.toArray(new Integer[] {7, 8, 9, 10 , 11, 12, 13})));
        assertEquals("[1, 2, 3, 4, 5, 6, null, 14, 15]",
                Arrays.toString(list.toArray(new Integer[] {7, 8, 9, 10, 11, 12, 13, 14, 15})));
    }

    @Test
    void toArrayWithArrayParameter_ifListIsEmpty() {
        List<Integer> testList = new MyConjugateList<>(new ArrayList<>(), new ArrayList<>());
        assertEquals("[null, 2, 3]", Arrays.toString(testList.toArray(new Integer[] {1, 2, 3})));
        assertEquals("[]", Arrays.toString(testList.toArray(new Integer[0])));
    }

    @Test
    void toArrayWithArrayParameter_ifCast() {
        List<Integer> testList = new MyConjugateList<>(new ArrayList<>(List.of(1)), new ArrayList<>(List.of(2)));
        assertEquals("[1, 2, null, 4]", Arrays.toString(testList.toArray(new Number[] {1, 2, 3, 4})));
        assertEquals("[1, 2]", Arrays.toString(testList.toArray(new Number[0])));

        List<Number> testList2 = new MyConjugateList<>(new ArrayList<>(List.of(1)), new ArrayList<>(List.of(2)));
        assertEquals("[1, 2, null, 4]", Arrays.toString(testList2.toArray(new Integer[] {1, 2, 3, 4})));
        assertEquals("[1, 2]", Arrays.toString(testList2.toArray(new Integer[0])));
    }

    @Test
    void add() {
        assertTrue(list.add(99));
        assertEquals(7, list.size());
        assertEquals(99, list.get(6));
    }

    @Test
    void remove() {
        assertTrue(list.remove((Integer) 4));
        assertEquals(5, list.size());
        assertEquals(6, list.get(4));

        assertFalse(list.remove((Integer) 99));

        assertTrue(list.remove((Integer) 6));
        assertEquals(4, list.size());
        assertEquals(5, list.get(3));
    }

    @Test
    void remove_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.remove((Integer) 1));
    }

    @Test
    void containsAll() {
        assertTrue(list.containsAll(Arrays.asList(1, 3, 4)));
        assertFalse(list.containsAll(Arrays.asList(1, 3, 4, 99)));
    }

    @Test
    void addAll() {
        assertTrue(list.addAll(Arrays.asList(7, 8)));
        assertEquals(8, list.size());
    }

    @Test
    void addAllWithIndex() {
        assertTrue(list.addAll(3, Arrays.asList(7, 8)));
        assertEquals(8, list.size());
        assertEquals(7, list.get(3));
        assertEquals(6, list.get(7));

        assertTrue(list.addAll(8, List.of(99)));
        assertEquals(9, list.size());
        assertEquals(99, list.get(8));
        assertFalse(list.addAll(8, new ArrayList<>()));
    }

    @Test
    void addAllWithIndex_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.addAll(2, Arrays.asList(7, 8)));
    }

    @Test
    void removeAll() {
        assertTrue(list.removeAll(Arrays.asList(4, 5)));
        assertEquals(4, list.size());
        assertEquals(6, list.get(3));
    }

    @Test
    void removeAll_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.removeAll(Arrays.asList(1, 5, 6)));
    }

    @Test
    void retainAll() {
        assertTrue(list.retainAll(Arrays.asList(1, 2, 3, 5)));
        assertEquals(4, list.size());
        assertEquals(5, list.get(3));
    }

    @Test
    void retainAll_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.retainAll(Arrays.asList(1, 2, 5)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 3, 5})
    void get(int index) {
        assertEquals(index + 1, list.get(index));
    }

    @Test
    void get_shouldThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(6));
    }

    @Test
    void set() {
        assertEquals(4, list.set(3, 99));
        assertEquals(99, list.get(3));
    }

    @Test
    void set_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.set(0, 99));
    }

    @Test
    void addWithIndex() {
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
    void add_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.add(0, 99));
    }

    @Test
    void removeWithIndex() {
        assertEquals(4, list.remove(3));
        assertEquals(5, list.get(3));
        assertEquals(6, list.get(4));

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(2));

        assertEquals(6, list.remove(4));
    }

    @Test
    void removeWithIndex_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> list.remove(0));
    }

    @Test
    void indexOf() {
        List<Integer> testList = new MyConjugateList<>(
                new ArrayList<>(Arrays.asList(1, 2)), new ArrayList<>(Arrays.asList(2, 2, 3)));
        assertEquals(1, testList.indexOf(2));
        assertEquals(4, testList.indexOf(3));
        assertEquals(-1, testList.indexOf(99));
    }

    @Test
    void lastIndexOf() {
        List<Integer> testList = new MyConjugateList<>(
                new ArrayList<>(Arrays.asList(1, 2)), new ArrayList<>(Arrays.asList(2, 2, 3)));
        assertEquals(3, testList.lastIndexOf(2));
        assertEquals(4, testList.lastIndexOf(3));
        assertEquals(-1, testList.lastIndexOf(99));
    }
}