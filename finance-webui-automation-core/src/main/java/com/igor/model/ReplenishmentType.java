package com.igor.model;

public enum ReplenishmentType {
    MONTHLY("monthly"),
    QUARTERLY("quarterly"),
    YEARLY("yearly"),
    NONE("NONE");

    private String type;

    ReplenishmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
