package com.epam.task3.util;

/**
 * String wrapper, it's used like key for associative collections like {@link java.util.HashMap} or {@link java.util.HashSet}
 * where used mainstream method hashCode(). Сan expand, example its subclasses {@link StrKeyBySumChar} and {@link StrKeyByLength}
 * which describe a specific hashcode generation policy.<p>
 *
 * @see StrKeyBySumChar
 * @see StrKeyByLength
 * @author Oleksii Kushch
 */
public class StrKey {
    public static final int HASHCODE_FOR_NULL = 0;
    private final String key;

    public StrKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
