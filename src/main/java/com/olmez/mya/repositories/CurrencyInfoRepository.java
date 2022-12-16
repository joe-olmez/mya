package com.olmez.mya.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.olmez.mya.model.CurrencyInfo;

public interface CurrencyInfoRepository extends BaseObjectRepository<CurrencyInfo> {

    @Query("SELECT t FROM CurrencyInfo t WHERE t.date = ?1 AND t.deleted = false ORDER BY t.date DESC")
    List<CurrencyInfo> findCurrencyInfoByDate(LocalDate date);

    default CurrencyInfo findByDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        List<CurrencyInfo> infos = findCurrencyInfoByDate(date);
        if (infos.isEmpty()) {
            return null;
        }

        if (infos.size() > 1) {
            // keep latest one
            for (int i = 1; i < infos.size(); i++) {
                infos.get(i).setDeleted(true);
            }
            saveAll(infos);
        }
        return infos.get(0);

    }

    @Override
    @Query("SELECT t FROM CurrencyInfo t WHERE t.deleted = false ORDER BY t.date DESC")
    List<CurrencyInfo> findAll();

    default CurrencyInfo getLatest() {
        var list = findAll();
        return !list.isEmpty() ? list.get(0) : null;
    }

}
