package com.olmez.mya.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.olmez.mya.currency.parser.DoubleDeserializer;
import com.olmez.mya.model.enums.CurrencyCode;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CurrencyRate extends BaseObject {

    private LocalDate date;
    private double amount = 1.0;
    private CurrencyCode baseCode = CurrencyCode.USD;
    private @Nullable Double cad;
    private @Nullable Double eur;
    private @Nullable Double gbp;
    private @Nullable Double jpy;
    private @Nullable Double tryy;
    /**
     * for test purposing {@link DoubleDeserializer}
     */
    @JsonSerialize(using = DoubleDeserializer.class)
    private @Nullable Double usd;

    public CurrencyRate(LocalDate date, @Nullable Double cad, @Nullable Double eur, @Nullable Double gbp,
            @Nullable Double jpy, @Nullable Double tryy, @Nullable Double usd) {
        this.date = date;
        this.cad = cad;
        this.eur = eur;
        this.gbp = gbp;
        this.jpy = jpy;
        this.tryy = tryy;
        this.usd = usd;
    }

    @Override
    public String toString() {
        return "Currency rates on " + this.date;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }
}
