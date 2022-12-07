package com.olmez.mya.model;

import java.time.LocalDate;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.olmez.mya.model.enums.CurrencyCode;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CurrencyInfo extends BaseObject {

    private LocalDate date;
    private double amount = 1.0;
    private CurrencyCode baseCode = CurrencyCode.USD;
    private @Nullable Double aUD;
    private @Nullable Double cAD;
    private @Nullable Double cHF;
    private @Nullable Double eUR;
    private @Nullable Double gBP;
    private @Nullable Double uSD;
    private @Nullable Double jPY;
    private @Nullable Double rUB;
    private @Nullable Double tRY;

    public CurrencyInfo(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Currency Info on %s", date);
    }
}
