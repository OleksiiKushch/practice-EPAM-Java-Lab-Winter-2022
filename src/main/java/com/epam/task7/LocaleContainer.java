package com.epam.task7;

import com.epam.task4.constants.ShopLiterals;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The class that contain (hold) all locales which allow an application {@link com.epam.task4.MainApp}.
 * An associative container ({@link #container}) that contains all locale, takes a locale
 * in string format as a key and a class object {@link Locale} as a value.
 *
 * Important: for correct method working {@link #viewExistingLocales} needed:
 * at the initialization container must accept three parameters as a key
 * in that order (full, short and native string interpretation locale).
 * example
 * {
 *     container.put("ukrainian", new Locale("ua"));
 *     container.put("ua", new Locale("ua"));
 *     container.put("Український", new Locale("ua"));
 * }
 *
 * Noted: native - interpretation locale in the indicated language
 * @author Oleksii Kushch
 */
public class LocaleContainer {
    private final Map<String, Locale> container;

    public LocaleContainer() {
        container = new LinkedHashMap<>();
    }

    public void put(String localeKey, Locale locale) {
        container.put(localeKey, locale);
    }

    public boolean isContainLocale(String localeKey) {
        return container.containsKey(localeKey) ||
                // because native locale key capitalized
                container.containsKey(localeKey.substring(0, 1).toUpperCase().concat(localeKey.substring(1)));
    }

    public Locale getLocaleByKey(String localeKey) {
        Locale result = container.get(localeKey);
        if (Objects.isNull(result)) {
            // because native locale key capitalized
            result = container.get(localeKey.substring(0, 1).toUpperCase().concat(localeKey.substring(1)));
        }
        return result;
    }

    /**
     * print all locales by format: "full (short) [native]" by sorted order
     * example: "russian (ru) [Русский]"
     */
    public void viewExistingLocales() {
        container.entrySet().stream().collect(Collectors.groupingBy(entry -> entry.getValue().getLanguage()))
                .values().stream().sorted(Comparator.comparing(locale -> locale.get(0).getKey()))
                .map(list -> list.stream().map(Map.Entry::getKey).collect(Collectors.toList()))
                .forEach(list -> System.out.printf(ShopLiterals.BASE_OUTPUT_FORMAT_LOCALE, list.toArray()));
    }
}
