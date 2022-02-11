package task1.subtask2;

import task1.subtask1.Book;
import task1.subtask1.EBook;
import task1.subtask1.PaperBook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Book book1 = new EBook(1L, "Verity", "Colleen Hoover", new BigDecimal("10.99"), "English", 1536);
        Book book2 = new EBook(1L, "Abandoned in Death", "J.D. Robb", new BigDecimal("14.99"), "English", 6159);
        Book book3 = new PaperBook(1L, "Ugly Love", "Colleen Hoover", new BigDecimal("9.68"), "English",
                new PaperBook.Dimension(8.25F, 5, 0.9F), PaperBook.CoverType.SOFT);

        MyList<Book> list = new MyList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);

        Iterator<Book> it = list.filteredIterator(e -> e.getAuthor().equals("Colleen Hoover"));
        while (it.hasNext())
            System.out.println(it.next().getTitle());
    }
}
