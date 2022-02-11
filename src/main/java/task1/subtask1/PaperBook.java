package task1.subtask1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class PaperBook extends Book {
    private static final long serialVersionUID = 4487177673978321695L;

    private Dimension dimension;
    private CoverType coverType;

    public PaperBook() {}

    public PaperBook(Long id, String title, String author, BigDecimal price, String language,
                     Dimension dimension, CoverType coverType) {
        super(id, title, author, price, language);
        this.dimension = dimension;
        this.coverType = coverType;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public CoverType getCoverType() {
        return coverType;
    }

    public void setCoverType(CoverType coverType) {
        this.coverType = coverType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaperBook)) return false;
        if (!super.equals(o)) return false;
        PaperBook paperBook = (PaperBook) o;
        return Objects.equals(dimension, paperBook.dimension) && coverType == paperBook.coverType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dimension, coverType);
    }

    public static class Dimension implements Serializable {
        private static final long serialVersionUID = 2537098970351573874L;

        private float x; // inches
        private float y; // inches
        private float z; // inches

        public Dimension() {}

        public Dimension(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getZ() {
            return z;
        }

        public void setZ(float z) {
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Dimension)) return false;
            Dimension dimension = (Dimension) o;
            return Float.compare(dimension.x, x) == 0
                    && Float.compare(dimension.y, y) == 0
                    && Float.compare(dimension.z, z) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }

    public enum CoverType {
        SOFT, HARD, DUST_JACKET, CHROME_ERSATZ
    }
}
