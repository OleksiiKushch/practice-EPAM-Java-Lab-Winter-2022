package com.epam.task1.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class EReader extends Commodity {
    private static final long serialVersionUID = -5185587079239564593L;

    protected String model;
    protected float displaySize;      // inches
    protected int storageGB;
    protected int resolutionPPI;

    public EReader() {}

    public EReader(Long id, BigDecimal price, String model, float displaySize, int storageGB, int resolutionPPI) {
        super(id, price);
        this.model = model;
        this.displaySize = displaySize;
        this.storageGB = storageGB;
        this.resolutionPPI = resolutionPPI;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(float displaySize) {
        this.displaySize = displaySize;
    }

    public int getStorageGB() {
        return storageGB;
    }

    public void setStorageGB(int storageGB) {
        this.storageGB = storageGB;
    }

    public int getResolutionPPI() {
        return resolutionPPI;
    }

    public void setResolutionPPI(int resolutionPPI) {
        this.resolutionPPI = resolutionPPI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EReader)) return false;
        if (!super.equals(o)) return false;
        EReader eReader = (EReader) o;
        return Float.compare(eReader.displaySize, displaySize) == 0 &&
                storageGB == eReader.storageGB &&
                resolutionPPI == eReader.resolutionPPI &&
                Objects.equals(model, eReader.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, displaySize, storageGB, resolutionPPI);
    }

    @Override
    public String toString() {
        return "EReader{" +
                "id=" + id +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", displaySize=" + displaySize +
                ", storageGB=" + storageGB +
                ", resolutionPPI=" + resolutionPPI +
                '}';
    }
}
