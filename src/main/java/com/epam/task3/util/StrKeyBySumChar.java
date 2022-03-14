package com.epam.task3.util;

/**
 * String wrapper, it's used like key for associative collections like {@link java.util.HashMap} or {@link java.util.HashSet}
 * where used mainstream method hashCode(). See detail hashcode generation policy in method {@link #hashCode}.<p>
 *
 * @author Oleksii Kushch
 */
public class StrKeyBySumChar extends StrKey {
    /** Quantity first char of string key for generate hashcode */
    private static final int QUANTITY_FIRST_CHAR_FOR_HASHCODE = 4;

    public StrKeyBySumChar(String key) {
        super(key);
    }

    /** hashcode equals sum of the first {@link #QUANTITY_FIRST_CHAR_FOR_HASHCODE} characters of the string key */
    @Override
    public int hashCode() {
        return getKey() != null ? getKey().chars().limit(QUANTITY_FIRST_CHAR_FOR_HASHCODE).sum() : HASHCODE_FOR_NULL;
    }
}
