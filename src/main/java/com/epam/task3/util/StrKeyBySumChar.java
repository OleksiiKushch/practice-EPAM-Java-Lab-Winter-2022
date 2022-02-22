package com.epam.task3.util;

public class StrKeyBySumChar extends StrKey {
    public StrKeyBySumChar(String key) {
        super(key);
    }

    private static final int QUANTITY_FIRST_CHAT_FOR_HASHCODE = 4;

    @Override
    public int hashCode() {
        return super.key != null ? super.key.chars().limit(QUANTITY_FIRST_CHAT_FOR_HASHCODE).sum() : HASHCODE_FOR_NULL;
    }
}
