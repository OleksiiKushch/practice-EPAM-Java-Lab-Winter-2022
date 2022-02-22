package com.epam.task3.util;

public class StrKeyByLength extends StrKey {
    public StrKeyByLength(String key) {
        super(key);
    }

    @Override
    public int hashCode() {
        return key != null ? key.length() : HASHCODE_FOR_NULL;
    }
}
