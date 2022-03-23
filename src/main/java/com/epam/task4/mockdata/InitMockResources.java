package com.epam.task4.mockdata;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task1.entity.EReader;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksii Kushch
 */
public class InitMockResources {
    public static void initProductCatalog(List<Commodity> productCatalog) {
        productCatalog.add(new Book(1L, "Verity (Colleen H.)", new BigDecimal("10.99"), 100,
                "Verity", "Colleen Hoover", "English", 239));
        productCatalog.add(new Book(2L, "Abandoned in Death (J.D. Robb)", new BigDecimal("14.99"), 30,
                "Abandoned in Death", "J.D. Robb", "English", 615));
        productCatalog.add(new Audiobook(3L, "Ugly Love (Colleen H.)", new BigDecimal("9.68"), 45,
                "Ugly Love", "Colleen Hoover", "English", 415,
                12863, 612, "Jim Dale"));
        productCatalog.add(new EReader(4L, "E-Reader12 (GDr-512)", new BigDecimal("28.98"), 70,
                "GDr-512", 7.2F, 8, 320));
        productCatalog.add(new EReader(5L, "E-Reader13a+ (GDr-513a)", new BigDecimal("36.68"), 30,
                "GDr-513a", 8.0F, 8, 360));
        productCatalog.add(new Book(6L, "Family Money (Chad Z.)", new BigDecimal("4.99"), 10,
                "Family Money", "Chad Zunker", "English", 239));
    }

    public static void initOrderCatalog(Map<LocalDateTime, List<Commodity>> orderCatalog) {
        orderCatalog.put(LocalDateTime.of(2022, 2, 21, 14, 50, 0),
                new ArrayList<>(Arrays.asList(
                        new Book(1L, "Verity (Colleen H.)", new BigDecimal("10.99"), 2,
                                "Verity", "Colleen Hoover", "English", 239),
                        new Book(2L, "Abandoned in Death (J.D. Robb)", new BigDecimal("14.99"), 1,
                                "Abandoned in Death", "J.D. Robb", "English", 615)
                )));
        orderCatalog.put(LocalDateTime.of(2022, 2, 21, 17, 20, 0),
                new ArrayList<>(Collections.singletonList(
                        new EReader(4L, "E-Reader12 (GDr-512)", new BigDecimal("28.98"), 1,
                                "GDr-512", 7.2F, 8, 320)
                )));
        orderCatalog.put(LocalDateTime.of(2022, 2, 22, 9, 15, 0),
                new ArrayList<>(Collections.singletonList(
                        new Audiobook(3L, "Ugly Love (Colleen H.)", new BigDecimal("9.68"),2,
                                "Ugly Love", "Colleen Hoover", "English", 415,
                                12863, 612, "Jim Dale")
                )));
        orderCatalog.put(LocalDateTime.of(2022, 2, 23, 19, 30, 0),
                new ArrayList<>(Arrays.asList(
                        new Book(2L, "Abandoned in Death (J.D. Robb)", new BigDecimal("14.99"), 1,
                                "Abandoned in Death", "J.D. Robb", "English", 615),
                        new Audiobook(3L, "Ugly Love (Colleen H.)", new BigDecimal("9.68"), 1,
                                "Ugly Love", "Colleen Hoover", "English", 415,
                                12863, 612, "Jim Dale")
                )));
    }
}
