package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.olmez.mya.model.CurrencyRate;
import com.olmez.mya.model.enums.CurrencyCode;
import com.olmez.mya.repositories.CurrencyRateRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyAPIService apiService;
    private final CurrencyRateRepository repository;

    @Override
    public List<CurrencyRate> update(LocalDate startInclusive, LocalDate endInclusive)
            throws InterruptedException, IOException {
        if (endInclusive == null || endInclusive.isAfter(LocalDate.now())) {
            endInclusive = LocalDate.now();
        }

        if (startInclusive == null) {
            startInclusive = endInclusive;
        }

        if (endInclusive.isBefore(startInclusive)) {
            return Collections.emptyList();
        }

        // max 99 call pear day
        if (startInclusive.isBefore(endInclusive.minusMonths(3))) {
            startInclusive = endInclusive.minusMonths(3);
        }

        List<CurrencyRate> rates = new ArrayList<>();
        LocalDate curDate = startInclusive;

        while (curDate.isBefore(endInclusive.plusDays(1))) {
            rates.add(update(curDate));
            curDate = curDate.plusDays(1);
        }
        return rates;
    }

    @Override
    @Transactional
    public CurrencyRate update(LocalDate date) throws IOException, InterruptedException {
        CurrencyRate existing = repository.findByDate(date);
        if (existing != null) {
            log.info("Data exist on {}", existing.getDate());
            return existing;
        }

        CurrencyRate rate = apiService.update(date);
        if (rate != null) {
            rate = repository.save(rate);
        }
        return rate;
    }

    // *** This section for CurrencyRestController ***
    @Override
    public List<CurrencyRate> getAllRates() {
        return repository.findAll();
    }

    @Override
    public boolean createCurrencyRate(CurrencyRate rate) {
        return repository.save(rate) != null;
    }

    @Override
    public CurrencyRate findCurrencyRateById(Long id) {
        if (id == null) {
            return null;
        }
        return repository.getById(id);
    }

    @Override
    public boolean deleteCurrencyRate(Long ciId) {
        CurrencyRate existing = findCurrencyRateById(ciId);
        if (existing == null) {
            return false;
        }
        repository.deleted(existing);
        return existing.isDeleted();
    }

    @Override
    public CurrencyRate updateCurrencyRate(Long id, CurrencyRate rateDetails) {
        CurrencyRate existing = findCurrencyRateById(id);
        if (existing == null) {
            return null;
        }
        updateExisting(existing, rateDetails);
        return repository.save(existing);
    }

    @Override
    public CurrencyRate findCurrencyRateByDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return repository.findByDate(date);
    }

    private CurrencyRate updateExisting(CurrencyRate existing, CurrencyRate given) {
        existing.setDate(given.getDate());
        existing.setAmount(given.getAmount());
        existing.setBaseCode(given.getBaseCode());
        existing.setCad(given.getCad());
        existing.setEur(given.getEur());
        existing.setGbp(given.getGbp());
        existing.setUsd(given.getUsd());
        existing.setJpy(given.getJpy());
        existing.setTryy(given.getTryy());
        log.info("Updated existing currency rate: {}", existing.getDate());
        return existing;
    }

    @Override
    public Double convert(CurrencyWrapper curWrapper) {
        if (curWrapper == null) {
            return null;
        }
        var map = rateMap(curWrapper.getDate());
        if (map.isEmpty()) {
            return null;
        }

        var fromCode = curWrapper.getFrom();
        var toCode = curWrapper.getTo();
        var amount = curWrapper.getAmount();

        Double converted;
        if (fromCode == CurrencyCode.USD) {
            converted = amount * map.get(toCode);
        } else {
            converted = (amount * map.get(toCode)) / map.get(fromCode);
        }
        return converted;
    }

    private Map<CurrencyCode, Double> rateMap(LocalDate date) {
        Map<CurrencyCode, Double> map = new HashMap<>();
        var rate = repository.findByDate(date);
        if (rate != null) {
            map.put(CurrencyCode.CAD, rate.getCad());
            map.put(CurrencyCode.EUR, rate.getEur());
            map.put(CurrencyCode.GBP, rate.getGbp());
            map.put(CurrencyCode.JPY, rate.getJpy());
            map.put(CurrencyCode.TRY, rate.getTryy());
            map.put(CurrencyCode.USD, rate.getUsd());
        }
        return map;
    }

}
