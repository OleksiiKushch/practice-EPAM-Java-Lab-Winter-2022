package com.epam.task1.util;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyListTest {

    private MyList<Book> list;

    @BeforeEach
    void init() {
        list = new MyList<>();
        list.add(new Book(1L, new BigDecimal("10.99"), "Verity", "Colleen Hoover", "English", 239));
        list.add(new Book(2L, new BigDecimal("14.99"), "Abandoned in Death", "J.D. Robb", "English", 615));
        list.add(new Audiobook(3L, new BigDecimal("9.68"), "Ugly Love", "Colleen Hoover", "English", 415,
                12863, 612, "Jim Dale"));
    }

    @Test
    void addWithIndex() {
        list.add(1, new Book());
        assertEquals(4, list.size());

        list.add(4, new Book());
        assertEquals(5, list.size());

        // test when capacity grow
        list.add(5, new Book()); // <- capacity grow here
        assertEquals(6, list.size());
    }

    @Test
    void add() {
        assertTrue(list.add(new Audiobook()));
        assertEquals(4, list.size());

        // test when capacity grow
        list.add(new Book());
        list.add(new Book()); // <- capacity grow here
        assertEquals(6, list.size());
    }

    @Test
    void get() {
        assertEquals("Ugly Love", list.get(2).getTitle());
        assertEquals("Verity", list.get(0).getTitle());
    }

    @Test
    void removeWithIndex() {
        assertEquals("Abandoned in Death", list.remove(1).getTitle());
        assertEquals(2, list.size());

        assertEquals("Ugly Love", list.remove(1).getTitle());
        assertEquals(1, list.size());
    }

    @Test
    void remove() {
        assertTrue(list.remove(new Book(1L, new BigDecimal("10.99"), "Verity", "Colleen Hoover", "English", 239)));
        assertEquals(2, list.size());

        assertFalse(list.remove(new Audiobook()));
        assertEquals(2, list.size());
    }

    @Test
    void iterator() {
        Iterator<Book> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Verity", iterator.next().getTitle());
        iterator.next();
        assertEquals("Ugly Love", iterator.next().getTitle());
        assertFalse(iterator.hasNext());
    }

    @Test
    void filteredIterator() {
        Iterator<Book> iterator = list.filteredIterator(e -> e.getAuthor().equals("Colleen Hoover"));
        assertEquals("Verity", iterator.next().getTitle());
        assertEquals("Ugly Love", iterator.next().getTitle());
        assertFalse(iterator.hasNext());
    }
}