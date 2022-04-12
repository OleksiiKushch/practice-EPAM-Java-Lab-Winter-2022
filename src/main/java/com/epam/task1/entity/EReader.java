package com.epam.task1.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Bean class representation of the e-reader (e-book reader or e-book device)
 * <p>(mobile electronic device designed to read digital e-books).
 *
 * @author Oleksii Kushch
 */
public class EReader extends Commodity {
    private static final long serialVersionUID = -5185587079239564593L;

    /**
     * the model concrete e-reader (title name)
     */
    private String model;
    /**
     * the display size concrete e-reader (measured in inches)
     */
    private float displaySize;
    /**
     * the on-device (e-reader) storage (measured in GB)
     */
    private int storageGB;
    /**
     * the display resolution concrete e-reader (measured in ppi)
     */
    private int resolutionPPI;

    public EReader() {
    }

    public EReader(Long id, String frontTitle, BigDecimal price,
                   String model, float displaySize, int storageGB, int resolutionPPI) {
        super(id, frontTitle, price);
        this.model = model;
        this.displaySize = displaySize;
        this.storageGB = storageGB;
        this.resolutionPPI = resolutionPPI;
    }

    public EReader(Long id, String frontTitle, BigDecimal price, Integer amount,
                   String model, float displaySize, int storageGB, int resolutionPPI) {
        super(id, frontTitle, price, amount);
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof EReader)) return false;
        if (!super.equals(object)) return false;
        EReader eReader = (EReader) object;
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
                "id=" + getId() +
                ", frontTitle='" + getFrontTitle() + '\'' +
                ", price=" + getPrice() +
                ", amount=" + getAmount() +
                ", model='" + model + '\'' +
                ", displaySize=" + displaySize +
                ", storageGB=" + storageGB +
                ", resolutionPPI=" + resolutionPPI +
                '}';
    }
}
