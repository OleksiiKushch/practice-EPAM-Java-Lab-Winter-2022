package com.epam.task2.util;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyCopyOnWriteListTest {

    @Test
    void iterator() {
        MyCopyOnWriteList<Integer> testMyList = new MyCopyOnWriteList<>();
        testMyList.add(1);
        testMyList.add(2);
        testMyList.add(3);

        Iterator<Integer> iterator = testMyList.iterator();
        testMyList.add(null);
        int numberOfIterations = 0;
        while (iterator.hasNext()) {
            iterator.next();
            numberOfIterations++;
        }
        assertEquals(3, numberOfIterations);
    }
}