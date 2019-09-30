package com.igor.model;

public enum Currency {
    UAH("UAH"),
    USD("USD"),
    EUR("EUR");

    private String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
