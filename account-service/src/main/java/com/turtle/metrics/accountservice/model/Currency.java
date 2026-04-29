package com.turtle.metrics.accountservice.model;

public enum Currency {
    USD, EUR, RUB;

    public static Currency getDefault() {
        return USD;
    }
}
