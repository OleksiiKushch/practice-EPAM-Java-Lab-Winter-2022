package com.epam.task3.util;

/**
 * String wrapper, it's used like key for associative collections like {@link java.util.HashMap} or {@link java.util.HashSet}
 * where used mainstream method hashCode(). See detail hashcode generation policy in method {@link #hashCode}.<p>
 *
 * @author Oleksii Kushch
 */
public class StrKeyByLength extends StrKey {
    public StrKeyByLength(String key) {
        super(key);
    }

    /** hashcode equals length of the string key */
    @Override
    public int hashCode() {
        return getKey() != null ? getKey().length() : HASHCODE_FOR_NULL;
    }
}
