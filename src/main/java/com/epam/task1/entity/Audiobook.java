package com.epam.task1.entity;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task7.create_product.ProductField;

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
    @ProductField(KEY = ShopLiterals.KEY_AUDIOBOOK_FILE_SIZE_MB)
    private Integer fileSizeMB;
    /**
     * the total time (duration or length) of listening concrete audiobook (measured in minutes)
     */
    @ProductField(KEY = ShopLiterals.KEY_AUDIOBOOK_LISTENING_TIME_MINUTES)
    private Integer listeningTimeMinutes;
    /**
     * the narrator concrete digital audiobook (main or first narrator to the list of audiobook narrators)
     */
    @ProductField(KEY = ShopLiterals.KEY_AUDIOBOOK_NARRATOR)
    private String narrator;

    public Audiobook() {
    }

    public Audiobook(Book book) {
        super(book.getId(), book.getFrontTitle(), book.getPrice(), book.getAmount(),
                book.getTitle(), book.getAuthor(), book.getLanguage(), book.getNumberOfPages());
    }

    public Audiobook(Long id, String frontTitle, BigDecimal price,
                     String title, String author, String language, Integer numberOfPages,
                     Integer sizeMB, Integer listeningLength, String narrator) {
        super(id, frontTitle, price, title, author, language, numberOfPages);
        this.fileSizeMB = sizeMB;
        this.listeningTimeMinutes = listeningLength;
        this.narrator = narrator;
    }

    public Audiobook(Long id, String frontTitle, BigDecimal price, Integer amount,
                     String title, String author, String language, Integer numberOfPages,
                     Integer sizeMB, Integer listeningLength, String narrator) {
        super(id, frontTitle, price, amount, title, author, language, numberOfPages);
        this.fileSizeMB = sizeMB;
        this.listeningTimeMinutes = listeningLength;
        this.narrator = narrator;
    }

    public Integer getFileSizeMB() {
        return fileSizeMB;
    }

    public void setFileSizeMB(Integer fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    public Integer getListeningTimeMinutes() {
        return listeningTimeMinutes;
    }

    public void setListeningTimeMinutes(Integer listeningTimeMinutes) {
        this.listeningTimeMinutes = listeningTimeMinutes;
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
        return fileSizeMB.equals(audioBook.fileSizeMB) &&
                listeningTimeMinutes.equals(audioBook.listeningTimeMinutes) &&
                Objects.equals(narrator, audioBook.narrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fileSizeMB, listeningTimeMinutes, narrator);
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
                ", fileSizeMB=" + fileSizeMB +
                ", listeningTimeMinutes=" + listeningTimeMinutes +
                ", narrator='" + narrator + '\'' +
                '}';
    }
}
