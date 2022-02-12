package com.epam.task1.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class AudioBook extends Book {
    private static final long serialVersionUID = 7869732606451640629L;

    protected int sizeMB;
    protected int listeningLength;    // minutes
    protected String narrator;

    public AudioBook() {}

    public AudioBook(Long id, BigDecimal price, String title, String author, String language, int numberOfPages,
                     int sizeMB, int listeningLength, String narrator) {
        super(id, price, title, author, language, numberOfPages);
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
        if (!(o instanceof AudioBook)) return false;
        if (!super.equals(o)) return false;
        AudioBook audioBook = (AudioBook) o;
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
                "id=" + id +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", id=" + id +
                ", price=" + price +
                ", sizeMB=" + sizeMB +
                ", listeningLength=" + listeningLength +
                ", narrator='" + narrator + '\'' +
                '}';
    }
}
