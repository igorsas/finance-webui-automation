package com.igor.model;

public enum TermType {
    MONTHS("months"),
    YEARS("years");

    private String type;

    TermType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
