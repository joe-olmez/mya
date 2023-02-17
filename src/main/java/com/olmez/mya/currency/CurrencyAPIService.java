package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;

import com.olmez.mya.model.CurrencyRate;

public interface CurrencyAPIService {

    CurrencyRate update() throws IOException, InterruptedException;

    CurrencyRate update(LocalDate date) throws IOException, InterruptedException;

}
