package com.olmez.mya.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.olmez.mya.model.CurrencyRate;

public interface CurrencyRateRepository extends BaseObjectRepository<CurrencyRate> {

    @Query("SELECT t FROM CurrencyRate t WHERE t.date = ?1 AND t.deleted = false ORDER BY t.date DESC")
    List<CurrencyRate> findCurrencyRateByDate(LocalDate date);

    @Override
    @Query("SELECT t FROM CurrencyRate t WHERE t.deleted = false ORDER BY t.date DESC")
    List<CurrencyRate> findAll();

    default CurrencyRate getLatest() {
        var list = findAll();
        return !list.isEmpty() ? list.get(0) : null;
    }

    default CurrencyRate findByDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        List<CurrencyRate> rates = findCurrencyRateByDate(date);
        if (rates.isEmpty()) {
            return null;
        }

        if (rates.size() > 1) {
            // keep latest one
            for (int i = 1; i < rates.size(); i++) {
                rates.get(i).setDeleted(true);
            }
            saveAll(rates);
        }
        return rates.get(0);
    }

}
