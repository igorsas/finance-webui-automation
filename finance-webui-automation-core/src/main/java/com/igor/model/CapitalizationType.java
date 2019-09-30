package com.igor.model;

public enum CapitalizationType {
    MONTHLY("monthly"),
    QUARTERLY("quarterly"),
    YEARLY("yearly"),
    NONE("NONE");

    private String type;

    CapitalizationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
