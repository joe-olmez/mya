package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.olmez.mya.model.CurrencyInfo;

public interface CurrencyService {

    CurrencyInfo update() throws IOException, InterruptedException;

    /**
     * Updates data as much as the number of days from today backward
     * 
     * @param numOfDays number of days
     * @return list of currency info
     * @throws IOException
     * @throws InterruptedException
     */
    List<CurrencyInfo> update(int numOfDays) throws InterruptedException, IOException;

    CurrencyInfo update(LocalDate date) throws IOException, InterruptedException;

    List<CurrencyInfo> update(LocalDate startDate, LocalDate endDate)
            throws InterruptedException, IOException;

    //
    List<CurrencyInfo> getCurrencyInfos();

    boolean addCurrencyInfo(CurrencyInfo newInfo);

    CurrencyInfo getCurrencyInfoById(Long ciId);

    boolean deleteCurrencyInfo(Long ciId);

    CurrencyInfo updateCurrencyInfo(Long existingInfoId, CurrencyInfo givenInfo);

    CurrencyInfo getCurrencyInfoByDate(LocalDate date);
}
