package com.olmez.mya.currency.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
public class CurRates {

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

    public class CAD extends CurDetail {
    }

    public class EUR extends CurDetail {
    }

    public class TRY extends CurDetail {
    }

    public class RUB extends CurDetail {
    }

    public class GBP extends CurDetail {
    }

    public class USD extends CurDetail {
    }

    public class AUD extends CurDetail {
    }

    public class CHF extends CurDetail {
    }

    public class JPY extends CurDetail {

    }

}
