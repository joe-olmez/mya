package com.olmez.mya.services.impl;

import org.springframework.stereotype.Service;

import com.olmez.mya.repo.CurrencyRateRepository;
import com.olmez.mya.repo.EmployeeRepository;
import com.olmez.mya.repo.UserRepository;
import com.olmez.mya.services.TestRepoCleanerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestRepoCleanerServiceImpl implements TestRepoCleanerService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final CurrencyRateRepository rateRepository;

    public void clear() {
        userRepository.deleteAll();
        employeeRepository.deleteAll();
        rateRepository.deleteAll();
        log.info("All test repositories has cleaned!");
    }

}
