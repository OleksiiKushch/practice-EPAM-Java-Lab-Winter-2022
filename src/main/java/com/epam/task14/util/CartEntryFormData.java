package com.epam.task14.util;

public class CartEntryFormData {
    private int productId;
    private int amount;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartEntryFormData{" +
                "productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
