package com.urianskui.studentweb.service.search_utils;

import lombok.Data;

@Data
public class SearchCriteria {

    private String key;
    private SearchOperation operation;
    private Object value;

    private boolean isOrPredicate;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        if (key.startsWith("'")) {
            isOrPredicate = true;
            key = key.replaceFirst("'", "");
        }
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public boolean isOrPredicate() {
        return isOrPredicate;
    }
}
