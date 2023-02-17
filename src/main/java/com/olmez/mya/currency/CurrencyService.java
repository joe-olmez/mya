package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.olmez.mya.model.CurrencyRate;

public interface CurrencyService {

    CurrencyRate update() throws IOException, InterruptedException;

    /**
     * Updates data as much as the number of days from today backward
     * 
     * @param numOfDays number of days
     * @return list of currency info
     * @throws IOException
     * @throws InterruptedException
     */
    List<CurrencyRate> update(int numOfDays) throws InterruptedException, IOException;

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
