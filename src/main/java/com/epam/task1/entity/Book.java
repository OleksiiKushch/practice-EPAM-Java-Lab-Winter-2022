package com.epam.task1.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Bean class representation of the book.
 *
 * @author Oleksii Kushch
 * @see Audiobook
 */
public class Book extends Commodity {
    private static final long serialVersionUID = 4564726723641617117L;

    /**
     * the title concrete book (name)
     */
    private String title;
    /**
     * the author concrete book (main or first author to the list of book authors)
     */
    private String author;
    /**
     * the language concrete book
     */
    private String language;
    /**
     * the number of pages concrete book
     */
    private int numberOfPages;

    public Book() {
    }

    public Book(Long id, String frontTitle, BigDecimal price,
                String title, String author, String language, int numberOfPages) {
        super(id, frontTitle, price);
        this.title = title;
        this.author = author;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }

    public Book(Long id, String frontTitle, BigDecimal price, Integer amount,
                String title, String author, String language, int numberOfPages) {
        super(id, frontTitle, price, amount);
        this.title = title;
        this.author = author;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, author, language, numberOfPages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", frontTitle='" + getFrontTitle() + '\'' +
                ", price=" + getPrice() +
                ", amount=" + getAmount() +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
