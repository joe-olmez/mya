package com.olmez.mya.currency.parser;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.olmez.mya.model.CurrencyInfo;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
public class CurrencyRoot {

    @JsonProperty("base_currency_code")
    private String baseCode;
    @JsonProperty("base_currency_name")
    private String name;
    private String amount;
    @JsonProperty("updated_date")
    private String updatedOn;
    private CurrencyRates rates;

    public CurrencyInfo getCurrencyInfo() {
        LocalDate date = LocalDate.parse(getUpdatedOn());
        CurrencyInfo info = new CurrencyInfo(date);
        info.addRate(rates.getAUD().getCode(), rates.getAUD().getRate());
        info.addRate(rates.getCAD().getCode(), rates.getCAD().getRate());
        info.addRate(rates.getCHF().getCode(), rates.getCHF().getRate());
        info.addRate(rates.getEUR().getCode(), rates.getEUR().getRate());
        info.addRate(rates.getGBP().getCode(), rates.getGBP().getRate());
        info.addRate(rates.getJPY().getCode(), rates.getJPY().getRate());
        info.addRate(rates.getRUB().getCode(), rates.getRUB().getRate());
        info.addRate(rates.getTRY().getCode(), rates.getTRY().getRate());
        info.addRate(rates.getUSD().getCode(), rates.getUSD().getRate());
        return info;
    }
}