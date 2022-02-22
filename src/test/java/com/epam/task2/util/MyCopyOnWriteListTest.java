package com.epam.task2.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyCopyOnWriteListTest {

    MyCopyOnWriteList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyCopyOnWriteList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void iterator() {
        Iterator<Integer> iterator = list.iterator();
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(3, numberOfIterations);
    }

    @Test
    void iteratorWhileModification_add() {
        Iterator<Integer> iterator = list.iterator();

        list.add(99);
        assertEquals(4, list.size());

        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(3, numberOfIterations);
    }

    @Test
    void iteratorWhileModification_remove() {
        Iterator<Integer> iterator = list.iterator();

        list.remove(0);
        assertEquals(2, list.size());

        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(3, numberOfIterations);
    }

    @Test
    void iteratorWhileModification_set() {
        Iterator<Integer> iterator = list.iterator();

        list.set(0, 99);

        assertEquals(1, iterator.next());
    }
}