package task1.subtask1;

import java.math.BigDecimal;
import java.util.Objects;

public class AudioBook extends EBook {
    private static final long serialVersionUID = 7869732606451640629L;

    private int listeningLength; // minutes
    private String narrator;

    public AudioBook() {}

    public AudioBook(Long id, String title, String author, BigDecimal price, String language,
                     long sizeKB, int listeningLength, String narrator) {
        super(id, title, author, price, language, sizeKB);
        this.listeningLength = listeningLength;
        this.narrator = narrator;
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
        return listeningLength == audioBook.listeningLength
                && Objects.equals(narrator, audioBook.narrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listeningLength, narrator);
    }
}
