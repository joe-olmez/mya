package com.olmez.mya.model;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;

import com.olmez.mya.model.enums.CurrencyCode;

import lombok.Getter;

@Entity
@Getter
public class CurrencyInfo extends BaseObject {

    private static final CurrencyCode BASE_CODE = CurrencyCode.USD;
    private LocalDate date;
    // TODO dosent save to repo
    private EnumMap<CurrencyCode, Double> rates = new EnumMap<>(CurrencyCode.class);

    public CurrencyInfo(LocalDate date) {
        this.date = date;
    }

    public void addRate(CurrencyCode code, String rate) {
        if (rate != null && !rate.isEmpty()) {
            rates.put(code, Double.valueOf(rate));
        }
    }

    public void addRate(CurrencyCode code, Double rate) {
        rates.put(code, rate);
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
