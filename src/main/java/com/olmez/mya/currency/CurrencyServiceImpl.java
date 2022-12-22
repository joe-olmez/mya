package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.olmez.mya.model.CurrencyInfo;
import com.olmez.mya.repositories.CurrencyInfoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyAPIService apiService;
    private final CurrencyInfoRepository curInfoRepository;

    @Override
    @Transactional
    public CurrencyInfo update(LocalDate date) throws IOException, InterruptedException {
        CurrencyInfo existing = curInfoRepository.findByDate(date);

        if ((existing != null) && (!date.isEqual(LocalDate.now()))) {
            log.info("Data exist on {}", existing.getDate());
            return existing;
        }

        CurrencyInfo info = apiService.update(date);
        if (info != null) {
            if ((existing != null) && (info.getDate().isEqual(LocalDate.now()))) {
                info = updateExisting(existing, info);
            }
            info = curInfoRepository.save(info);
            log.info("---Updated currency data - {}", info);
        }
        return info;
    }

    @Override
    @Transactional
    public CurrencyInfo update() throws IOException, InterruptedException {
        return update(LocalDate.now());
    }

    @Override
    @Transactional
    public List<CurrencyInfo> update(LocalDate startDateInclusive, LocalDate endDateInclusive)
            throws InterruptedException, IOException {
        if (endDateInclusive == null || endDateInclusive.isAfter(LocalDate.now())) {
            endDateInclusive = LocalDate.now();
        }

        if (startDateInclusive == null) {
            startDateInclusive = endDateInclusive;
        }

        if (endDateInclusive.isBefore(startDateInclusive)) {
            return Collections.emptyList();
        }

        // max 99 call pear day
        if (startDateInclusive.isBefore(endDateInclusive.minusMonths(3))) {
            startDateInclusive = endDateInclusive.minusMonths(3);
        }

        List<CurrencyInfo> infoList = new ArrayList<>();
        LocalDate curDate = startDateInclusive;

        while (curDate.isBefore(endDateInclusive.plusDays(1))) {
            infoList.add(update(curDate));
            curDate = curDate.plusDays(1);
        }
        return infoList;
    }

    // *** This section for CurrencyRController ***
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

    @Override
    public CurrencyInfo getCurrencyInfoByDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return curInfoRepository.findByDate(date);
    }

    @Override
    public List<CurrencyInfo> update(int numOfDays) throws InterruptedException, IOException {
        return update(LocalDate.now().minusDays(numOfDays), LocalDate.now());

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
        log.info("Updated existing data on {}", existing.getDate());
        return existing;
    }

}
