package com.epam.task4.mockdata;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.epam.task1.entity.Commodity;

import java.util.List;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.EReader;

public class MockProductCatalog {
    private static MockProductCatalog instance;

    public static MockProductCatalog getInstance() {
        if (instance == null) {
            instance = new MockProductCatalog();
        }
        return instance;
    }

    private final List<Commodity> productList  = new ArrayList<>();

    private MockProductCatalog() {
        initProductCatalog();
    }

    private void initProductCatalog() {
        productList.add(new Book(1L, "Verity (Colleen H.)", new BigDecimal("10.99"),
                        "Verity", "Colleen Hoover", "English", 239));
        productList.add(new Book(2L, "Abandoned in Death (J.D. Robb)", new BigDecimal("14.99"),
                "Abandoned in Death", "J.D. Robb", "English", 615));
        productList.add(new Audiobook(3L, "Ugly Love (Colleen H.)", new BigDecimal("9.68"),
                "Ugly Love", "Colleen Hoover", "English", 415,
                12863, 612, "Jim Dale"));
        productList.add(new EReader(4L, "E-Reader12 (GDr-512)", new BigDecimal("28.98"),
                "GDr-512", 7.2F, 8, 320));
        productList.add(new EReader(5L, "E-Reader13a+ (GDr-513a)", new BigDecimal("36.68"),
                "GDr-513a", 8.0F, 8, 360));
        productList.add(new Book(6L, "Family Money (Chad Z.)", new BigDecimal("4.99"),
                "Family Money", "Chad Zunker", "English", 239));
    }

    public List<Commodity> getProductList() {
        return productList;
    }

    public void printProductList() {
        productList.forEach(commodity -> System.out.println("(id: " + commodity.getId() + ") "
                + commodity.getClass().getSimpleName() + ": " + commodity.getFrontTitle() + ", price: "
                + commodity.getPrice()));
    }
}
