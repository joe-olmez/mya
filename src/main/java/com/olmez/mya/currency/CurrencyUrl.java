package com.olmez.mya.currency;

import java.time.LocalDate;

import com.olmez.mya.utility.UrlBuilder;

/**
 * https://api.getgeoapi.com/v2/currency/historical/2022-12-04?api_key=2f21b4d68e426aa26f52cba85798ac39fae0fbcf&format=json&from=USD
 */
public class CurrencyUrl {

    private static final String BASE_URL = "https://api.getgeoapi.com/v2/currency";
    private static final String API_KEY = "2f21b4d68e426aa26f52cba85798ac39fae0fbcf";
    private static final String FORMAT_JSON = "JSON";
    private static final String BASE_CURRENCY = "USD";

    private String url = "";

    public CurrencyUrl() {
        this(LocalDate.now().minusDays(1));
    }

    public CurrencyUrl(LocalDate date) {
        UrlBuilder urlBuilder = new UrlBuilder(BASE_URL + "/historical/");
        urlBuilder.setPath(date.toString());
        urlBuilder.addParameter("api_key", API_KEY);
        urlBuilder.addParameter("from", BASE_CURRENCY);
        urlBuilder.addParameter("format", FORMAT_JSON);
        this.url = urlBuilder.toString();
    }

    @Override
    public String toString() {
        return getUrl();
    }

    public String getUrl() {
        return this.url;
    }

}
