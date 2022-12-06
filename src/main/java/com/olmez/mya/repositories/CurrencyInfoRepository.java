package com.olmez.mya.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.olmez.mya.model.CurrencyInfo;

@Repository
public interface CurrencyInfoRepository extends BaseObjectRepository<CurrencyInfo> {

    @Query("SELECT u FROM CurrencyInfo u WHERE u.date = ?1 AND u.deleted = false")
    Optional<CurrencyInfo> findByDate(LocalDate date);

    @Override
    @Query("SELECT t FROM CurrencyInfo t WHERE t.deleted = false ORDER BY t.date DESC")
    List<CurrencyInfo> findAll();

    default CurrencyInfo getLatestRecord() {
        var list = findAll();
        return !list.isEmpty() ? list.get(0) : null;
    }
}
