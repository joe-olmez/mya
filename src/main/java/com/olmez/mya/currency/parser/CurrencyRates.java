package com.olmez.mya.currency.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
public class CurrencyRates {

    @JsonProperty("EUR")
    private EUR eUR;
    @JsonProperty("GBP")
    private GBP gBP;
    @JsonProperty("USD")
    private USD uSD;
    @JsonProperty("JPY")
    private JPY jPY;
    @JsonProperty("CAD")
    private CAD cAD;
    @JsonProperty("CHF")
    private CHF cHF;
    @JsonProperty("AUD")
    private AUD aUD;
    @JsonProperty("RUB")
    private RUB rUB;
    @JsonProperty("TRY")
    private TRY tRY;

    public class CAD extends CurrencyDetail {
    }

    public class EUR extends CurrencyDetail {
    }

    public class TRY extends CurrencyDetail {
    }

    public class RUB extends CurrencyDetail {
    }

    public class GBP extends CurrencyDetail {
    }

    public class USD extends CurrencyDetail {
    }

    public class AUD extends CurrencyDetail {
    }

    public class CHF extends CurrencyDetail {
    }

    public class JPY extends CurrencyDetail {

    }

}
