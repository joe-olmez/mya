package com.olmez.mya.currency.parser;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.olmez.mya.model.CurrencyInfo;
import com.olmez.mya.utility.MathUtils;

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
        info.setAUD(MathUtils.toDouble(rates.getAUD().getRate()));
        info.setCAD(MathUtils.toDouble(rates.getCAD().getRate()));
        info.setCHF(MathUtils.toDouble(rates.getCHF().getRate()));
        info.setEUR(MathUtils.toDouble(rates.getEUR().getRate()));
        info.setGBP(MathUtils.toDouble(rates.getGBP().getRate()));
        info.setJPY(MathUtils.toDouble(rates.getJPY().getRate()));
        info.setRUB(MathUtils.toDouble(rates.getRUB().getRate()));
        info.setTRY(MathUtils.toDouble(rates.getTRY().getRate()));
        info.setUSD(MathUtils.toDouble(rates.getUSD().getRate()));
        return info;
    }

}