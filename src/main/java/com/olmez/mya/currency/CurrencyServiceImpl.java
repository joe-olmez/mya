package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.olmez.mya.model.CurrencyInfo;
import com.olmez.mya.repositories.CurrencyInfoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyAPIService apiService;
    private final CurrencyInfoRepository curInfoRepository;

    @Override
    public CurrencyInfo update() throws IOException, InterruptedException {
        return update(LocalDate.now().minusDays(1));
    }

    @Override
    public CurrencyInfo update(LocalDate date) throws IOException, InterruptedException {
        CurrencyInfo existing = checkDB(date);
        if (existing != null) {
            return existing;
        }
        CurrencyInfo info = apiService.update(date);
        if (info != null) {
            info = curInfoRepository.save(info);
            log.info("---Updated currency data - {}", info);
        }
        return info;
    }

    @Override
    public List<CurrencyInfo> update(LocalDate startDate, LocalDate endDate)
            throws InterruptedException, IOException {
        if (endDate == null || endDate.isAfter(LocalDate.now().minusDays(1))) {
            endDate = LocalDate.now().minusDays(1);
        }

        if (startDate == null) {
            startDate = endDate;
        }

        if (endDate.isBefore(startDate)) {
            return Collections.emptyList();
        }

        if (startDate.isBefore(endDate.minusMonths(1))) {
            startDate = endDate.minusMonths(1);
        }

        List<CurrencyInfo> infoList = new ArrayList<>();
        LocalDate curDate = startDate;

        while (curDate.isBefore(endDate)) {
            infoList.add(update(curDate));
            curDate = curDate.plusDays(1);
        }
        return infoList;
    }

    private CurrencyInfo checkDB(LocalDate date) {
        var oInfo = curInfoRepository.findByDate(date);
        return (oInfo.isPresent()) ? oInfo.get() : null;
    }

    //

    @Override
    public List<CurrencyInfo> getCurrencyInfos() {
        return curInfoRepository.findAll();
    }

    @Override
    public boolean addCurrencyInfo(CurrencyInfo newInfo) {
        if (newInfo == null) {
            return false;
        }
        newInfo = curInfoRepository.save(newInfo);
        return newInfo.getId() != null;
    }

    @Override
    public CurrencyInfo getCurrencyInfoById(Long ciId) {
        if (ciId == null) {
            return null;
        }
        return curInfoRepository.getById(ciId);
    }

    @Override
    public boolean deleteCurrencyInfo(Long ciId) {
        CurrencyInfo existing = getCurrencyInfoById(ciId);
        if (existing == null) {
            return false;
        }
        curInfoRepository.deleted(existing);
        log.info("Deleted! {}", existing);
        return true;
    }

    @Override
    public CurrencyInfo updateCurrencyInfo(Long id, CurrencyInfo givenInfo) {
        CurrencyInfo existing = getCurrencyInfoById(id);
        if (existing == null) {
            return null;
        }
        updateExisting(existing, givenInfo);
        curInfoRepository.save(existing);
        log.info("Updated! {}", existing);
        return existing;
    }

    private CurrencyInfo updateExisting(CurrencyInfo existing, CurrencyInfo given) {
        existing.setDate(given.getDate());
        existing.setAmount(given.getAmount());
        existing.setBaseCode(given.getBaseCode());
        existing.setAUD(given.getAUD());
        existing.setCAD(given.getCAD());
        existing.setCHF(given.getCHF());
        existing.setEUR(given.getEUR());
        existing.setGBP(given.getGBP());
        existing.setUSD(given.getUSD());
        existing.setJPY(given.getJPY());
        existing.setRUB(given.getRUB());
        existing.setTRY(given.getTRY());
        return existing;
    }

    @Override
    public CurrencyInfo getCurrencyInfoByDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        var oInfo = curInfoRepository.findByDate(date);
        return oInfo.isPresent() ? oInfo.get() : null;
    }

}
