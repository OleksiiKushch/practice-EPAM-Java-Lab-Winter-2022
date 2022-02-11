package task1.subtask1;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Book extends Commodity {
    private static final long serialVersionUID = 4564726723641617117L;

    protected String title;
    protected String author;
    protected BigDecimal price;
    protected String language;

    protected Book() {}

    public Book(Long id, String title, String author, BigDecimal price, String language) {
        super(id);
        this.title = title;
        this.author = author;
        this.price = price;
        this.language = language;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(price, book.price)
                && Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, author, price, language);
    }
}
