package com.urianskui.studentweb.service.search_utils;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN;

    public static final String[] OPERATION_SET = {":", "!", ">", "<"};

    public static SearchOperation getSimpleOperation(char input) {
        switch (input) {
            case ':':
                return EQUALITY;
            case '!':
                return NEGATION;
            case '>':
                return GREATER_THAN;
            case '<':
                return LESS_THAN;
            default:
                return null;
        }
    }
}
