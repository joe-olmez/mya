package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.olmez.mya.model.CurrencyInfo;

public interface CurrencyService {

    CurrencyInfo update() throws IOException, InterruptedException;

    CurrencyInfo update(LocalDate date) throws IOException, InterruptedException;

    List<CurrencyInfo> update(LocalDate startDate, LocalDate endDate)
            throws InterruptedException, IOException;
}
