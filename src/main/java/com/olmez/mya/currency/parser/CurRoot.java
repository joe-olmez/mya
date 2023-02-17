package com.olmez.mya.currency.parser;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.olmez.mya.model.CurrencyRate;
import com.olmez.mya.utility.MathUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
@NoArgsConstructor
public class CurRoot {

    @JsonProperty("base_currency_code")
    private String baseCode;
    @JsonProperty("base_currency_name")
    private String name;
    private String amount;
    @JsonProperty("updated_date")
    private String updatedOn;
    private CurRates rates;

    public CurrencyRate createCurrencyRate() {
        return new CurrencyRate(
                LocalDate.parse(getUpdatedOn()),
                MathUtils.toDouble(rates.getCAD().getRate()),
                MathUtils.toDouble(rates.getEUR().getRate()),
                MathUtils.toDouble(rates.getGBP().getRate()),
                MathUtils.toDouble(rates.getJPY().getRate()),
                MathUtils.toDouble(rates.getTRY().getRate()),
                MathUtils.toDouble(rates.getUSD().getRate()));
    }

}