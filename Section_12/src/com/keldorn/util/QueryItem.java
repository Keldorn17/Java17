package com.keldorn.util;

public interface QueryItem {
    boolean matchFieldValue(String fieldName, String value);
}
