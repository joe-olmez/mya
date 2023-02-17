package com.olmez.mya.model.enums;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    CAD("CAD", "Canadian dollar"),
    EUR("EUR", "Euro"),
    GBP("GBP", "Pound sterling"),
    JPY("JPY", "Japanese yen"),
    TRY("TRY", "Turkish lira"),
    USD("USD", "United States dollar");

    private String name;
    private String description;

    private CurrencyCode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

}
