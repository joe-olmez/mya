package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.olmez.mya.model.CurrencyRate;

public interface CurrencyService {

    CurrencyRate update(LocalDate date) throws IOException, InterruptedException;

    List<CurrencyRate> update(LocalDate startDate, LocalDate endDate)
            throws InterruptedException, IOException;

    ////////////////////
    List<CurrencyRate> getAllRates();

    boolean createCurrencyRate(CurrencyRate rate);

    CurrencyRate findCurrencyRateById(Long id);

    CurrencyRate updateCurrencyRate(Long id, CurrencyRate rateDetails);

    boolean deleteCurrencyRate(Long id);

    CurrencyRate findCurrencyRateByDate(LocalDate date);
}
