package com.olmez.mya.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olmez.mya.repositories.ApiTokenRepository;
import com.olmez.mya.repositories.CurrencyInfoRepository;
import com.olmez.mya.repositories.EmployeeRepository;
import com.olmez.mya.repositories.LocationRepository;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.services.TestRepoCleanerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestRepoCleanerServiceImpl implements TestRepoCleanerService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApiTokenRepository apiTokenRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CurrencyInfoRepository curInfoRepository;

    public void clear() {
        locationRepository.deleteAll();
        apiTokenRepository.deleteAll();
        userRepository.deleteAll();
        employeeRepository.deleteAll();
        curInfoRepository.deleteAll();
        log.info("All test repositories has cleaned!");

    }

}
