package task1.subtask1;

import java.math.BigDecimal;
import java.util.Objects;

public class EBook extends Book {
    private static final long serialVersionUID = -8112701143438405348L;

    protected long sizeKB;

    public EBook() {}

    public EBook(Long id, String title, String author, BigDecimal price, String language,
                 long sizeKB) {
        super(id, title, author, price, language);
        this.sizeKB = sizeKB;
    }

    public long getSizeKB() {
        return sizeKB;
    }

    public void setSizeKB(long sizeKB) {
        this.sizeKB = sizeKB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EBook)) return false;
        if (!super.equals(o)) return false;
        EBook eBook = (EBook) o;
        return sizeKB == eBook.sizeKB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sizeKB);
    }
}
