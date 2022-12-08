package com.xrest.nchl.enums;

public enum ExceptionTypes {
    NOT_FOUND("NOT FOUND"),
    INVALID("INVALID FORMAT"),
    DELETE("COULD NOT DELETED"),
    TOKEN_EXPIRED("TOKEN HAS BEEN EXPIRED PLEASE RENEW THE TOKEN");
    public final String label;

    ExceptionTypes(String label) {
        this.label = label;
    }
}
