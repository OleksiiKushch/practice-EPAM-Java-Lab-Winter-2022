package com.epam.task3;

import com.epam.task1.entity.Audiobook;
import com.epam.task1.entity.Book;
import com.epam.task1.entity.Commodity;
import com.epam.task3.util.StrKey;
import com.epam.task3.util.StrKeyByLength;
import com.epam.task3.util.StrKeyBySumChar;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Commodity> testData = new ArrayList<>(Arrays.asList(
                new Book(1L, "Verity (Colleen H.)", new BigDecimal("10.99"),
                        "Verity", "Colleen Hoover", "English", 239),
                new Book(2L, "Abandoned in Death (Robb J.D.)", new BigDecimal("14.99"),
                        "Abandoned in Death", "J.D. Robb", "English", 615),
                new Audiobook(3L, "Colleen Hoover (Colleen H.)", new BigDecimal("9.68"),
                        "Ugly Love", "Colleen Hoover", "English", 415,
                        12863, 612, "Jim Dale")));

        test(new HashMap<>(), StrKeyByLength.class, testData);
        test(new HashMap<>(), StrKeyBySumChar.class, testData);
        test(new LinkedHashMap<>(), StrKeyByLength.class, testData);
        test(new LinkedHashMap<>(), StrKeyBySumChar.class, testData);
    }

    public static void test(Map<StrKey, Commodity> map, Class<? extends StrKey> typeStrKey, List<Commodity> data)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println(StringUtils.join("For ", map.getClass().getSimpleName(), ":"));
        System.out.println(StringUtils.join("\tFor ", typeStrKey.getSimpleName(), ":"));
        for (Commodity commodity : data) {
            map.put(typeStrKey.getConstructor(String.class).newInstance(commodity.getFrontTitle()), commodity);
        }
        map.values().forEach(b -> System.out.println(StringUtils.join("\t\t", b.getId(), " : ", b.getFrontTitle())));
    }
}
