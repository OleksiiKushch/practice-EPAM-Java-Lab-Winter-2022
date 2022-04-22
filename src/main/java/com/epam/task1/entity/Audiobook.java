package com.epam.task1.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Bean class representation of the digital e-book with audio voice acting.
 *
 * @author Oleksii Kushch
 */
public class Audiobook extends Book {
    private static final long serialVersionUID = 7869732606451640629L;

    /**
     * the total size of the digital e-book (all its components (files))
     */
    private int sizeMB;
    /**
     * the total time (duration or length) of listening concrete audiobook (measured in minutes)
     */
    private int listeningLength;
    /**
     * the narrator concrete digital audiobook (main or first narrator to the list of audiobook narrators)
     */
    private String narrator;

    public Audiobook() {
    }

    public Audiobook(Book book) {
        super(book.getId(), book.getFrontTitle(), book.getPrice(), book.getAmount(),
                book.getTitle(), book.getAuthor(), book.getLanguage(), book.getNumberOfPages());
    }

    public Audiobook(Long id, String frontTitle, BigDecimal price,
                     String title, String author, String language, int numberOfPages,
                     int sizeMB, int listeningLength, String narrator) {
        super(id, frontTitle, price, title, author, language, numberOfPages);
        this.sizeMB = sizeMB;
        this.listeningLength = listeningLength;
        this.narrator = narrator;
    }

    public Audiobook(Long id, String frontTitle, BigDecimal price, Integer amount,
                     String title, String author, String language, int numberOfPages,
                     int sizeMB, int listeningLength, String narrator) {
        super(id, frontTitle, price, amount, title, author, language, numberOfPages);
        this.sizeMB = sizeMB;
        this.listeningLength = listeningLength;
        this.narrator = narrator;
    }

    public int getSizeMB() {
        return sizeMB;
    }

    public void setSizeMB(int sizeMB) {
        this.sizeMB = sizeMB;
    }

    public int getListeningLength() {
        return listeningLength;
    }

    public void setListeningLength(int listeningLength) {
        this.listeningLength = listeningLength;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Audiobook)) return false;
        if (!super.equals(o)) return false;
        Audiobook audioBook = (Audiobook) o;
        return sizeMB == audioBook.sizeMB &&
                listeningLength == audioBook.listeningLength &&
                Objects.equals(narrator, audioBook.narrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sizeMB, listeningLength, narrator);
    }

    @Override
    public String toString() {
        return "AudioBook{" +
                "id=" + getId() +
                ", frontTitle='" + getFrontTitle() + '\'' +
                ", price=" + getPrice() +
                ", amount=" + getAmount() +
                ", title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", language='" + getLanguage() + '\'' +
                ", numberOfPages=" + getNumberOfPages() +
                ", sizeMB=" + sizeMB +
                ", listeningLength=" + listeningLength +
                ", narrator='" + narrator + '\'' +
                '}';
    }
}
