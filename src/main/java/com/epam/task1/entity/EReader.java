package com.epam.task1.entity;

import com.epam.task4.constants.ShopLiterals;
import com.epam.task7.create_product.ProductField;

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
    @ProductField(KEY = ShopLiterals.KEY_E_READER_MODEL)
    private String model;
    /**
     * the display size concrete e-reader (measured in inches)
     */
    @ProductField(KEY = ShopLiterals.KEY_E_READER_DISPLAY_SIZE_INCHES)
    private float displaySizeInches;
    /**
     * the on-device (e-reader) storage (measured in GB)
     */
    @ProductField(KEY = ShopLiterals.KEY_E_READER_STORAGE_CAPACITY_GB)
    private int storageCapacityGB;
    /**
     * the display resolution concrete e-reader (measured in ppi)
     */
    @ProductField(KEY = ShopLiterals.KEY_E_READER_SCREEN_RESOLUTION_PPI)
    private int screenResolutionPPI;

    public EReader() {
    }

    public EReader(Commodity commodity) {
        super(commodity.getId(), commodity.getFrontTitle(), commodity.getPrice(), commodity.getAmount());
    }

    public EReader(Long id, String frontTitle, BigDecimal price,
                   String model, float displaySize, int storageGB, int resolutionPPI) {
        super(id, frontTitle, price);
        this.model = model;
        this.displaySizeInches = displaySize;
        this.storageCapacityGB = storageGB;
        this.screenResolutionPPI = resolutionPPI;
    }

    public EReader(Long id, String frontTitle, BigDecimal price, Integer amount,
                   String model, float displaySize, int storageGB, int resolutionPPI) {
        super(id, frontTitle, price, amount);
        this.model = model;
        this.displaySizeInches = displaySize;
        this.storageCapacityGB = storageGB;
        this.screenResolutionPPI = resolutionPPI;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getDisplaySizeInches() {
        return displaySizeInches;
    }

    public void setDisplaySizeInches(float displaySizeInches) {
        this.displaySizeInches = displaySizeInches;
    }

    public int getStorageCapacityGB() {
        return storageCapacityGB;
    }

    public void setStorageCapacityGB(int storageCapacityGB) {
        this.storageCapacityGB = storageCapacityGB;
    }

    public int getScreenResolutionPPI() {
        return screenResolutionPPI;
    }

    public void setScreenResolutionPPI(int screenResolutionPPI) {
        this.screenResolutionPPI = screenResolutionPPI;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof EReader)) return false;
        if (!super.equals(object)) return false;
        EReader eReader = (EReader) object;
        return Float.compare(eReader.displaySizeInches, displaySizeInches) == 0 &&
                storageCapacityGB == eReader.storageCapacityGB &&
                screenResolutionPPI == eReader.screenResolutionPPI &&
                Objects.equals(model, eReader.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, displaySizeInches, storageCapacityGB, screenResolutionPPI);
    }

    @Override
    public String toString() {
        return "EReader{" +
                "id=" + getId() +
                ", frontTitle='" + getFrontTitle() + '\'' +
                ", price=" + getPrice() +
                ", amount=" + getAmount() +
                ", model='" + model + '\'' +
                ", displaySizeInches=" + displaySizeInches +
                ", storageCapacityGB=" + storageCapacityGB +
                ", screenResolutionPPI=" + screenResolutionPPI +
                '}';
    }
}
