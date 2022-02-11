package task1.subtask1;

import java.io.Serializable;
import java.util.Objects;

public abstract class Commodity implements Serializable {
    private static final long serialVersionUID = 5773634382252297178L;

    protected Long id;

    protected Commodity() {}

    protected Commodity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commodity)) return false;
        Commodity commodity = (Commodity) o;
        return Objects.equals(id, commodity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
