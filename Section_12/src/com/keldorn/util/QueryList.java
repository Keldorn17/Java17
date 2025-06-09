package com.keldorn.util;

import com.keldorn.model.generics.Students;

import java.util.ArrayList;
import java.util.List;

public class QueryList<T extends Students & QueryItem> extends ArrayList<T> {
    public QueryList() {

    }

    public QueryList(List<T> items) {
        super(items);
    }

    public static <T extends QueryItem> List<T> getMatches(List<T> items, String field, String value) {
        List<T> matches = new ArrayList<>();
        for (var item : items) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }

    public QueryList<T> getMatches(String field, String value) {
        QueryList<T> matches = new QueryList<>();
        for (var item : this) {
            if (item.matchFieldValue(field, value)) {
                matches.add(item);
            }
        }
        return matches;
    }
}
