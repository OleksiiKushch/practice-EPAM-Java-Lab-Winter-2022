package com.epam.task1.util;

import com.epam.task1.entity.AudioBook;
import com.epam.task1.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Book book1 = new Book(1L, new BigDecimal("10.99"), "Verity", "Colleen Hoover", "English", 239);
        Book book2 = new Book(2L, new BigDecimal("14.99"), "Abandoned in Death", "J.D. Robb", "English", 615);
        Book book3 = new AudioBook(3L, new BigDecimal("9.68"), "Ugly Love", "Colleen Hoover", "English", 415,
                12863, 612, "Jim Dale");

        MyList<Book> list = new MyList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);

        Iterator<Book> it = list.filteredIterator(e -> e.getAuthor().equals("Colleen Hoover"));
        while (it.hasNext()) {
            System.out.println(it.next().getTitle());
        }
    }
}
