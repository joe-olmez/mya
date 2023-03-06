package com.olmez.mya.services.impl;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.olmez.mya.currency.CurrencyService;
import com.olmez.mya.services.ScheduledService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduledServiceImpl implements ScheduledService {

    private final CurrencyService currencyService;

    /**
     * It allows updating currencies every day at 5:45 PM (17:45)
     * 
     * @throws Exception
     */
    @Scheduled(cron = "0 45 23 * * ?") // 23:45:00
    @Override
    @Transactional
    public void dailyUpdateCurrencyData() throws InterruptedException {
        log.info("Currency data are being pulled...");
        try {
            currencyService.update(LocalDate.now());
            log.info("Pulling currencies data is completed.");
        } catch (IOException e) {
            log.info("Failed to currency data pulling. {}", e.getMessage());
        }
    }

}
