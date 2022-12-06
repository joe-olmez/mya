package com.olmez.mya.currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.mya.model.CurrencyInfo;
import com.olmez.mya.model.enums.CurrencyCode;
import com.olmez.mya.repositories.CurrencyInfoRepository;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl service;
    @Spy
    private CurrencyAPIServiceImpl apiService = new CurrencyAPIServiceImpl();
    @Mock
    private CurrencyInfoRepository currencyInfoRepository;

    private String jsonResource = "/currency/rates.json";

    @Test
    void testUpdate_With_Date() throws IOException, InterruptedException {
        // arrange
        apiService.setTestMode(true);
        apiService.setTestResource(jsonResource);

        var date = LocalDate.of(2022, 12, 6);

        // act
        var retVal = apiService.update(date);

        // assert
        var rate = retVal.getRates();
        assertThat(rate.get(CurrencyCode.CAD).doubleValue()).isEqualTo(1.3468);
        assertThat(rate.get(CurrencyCode.JPY).doubleValue()).isEqualTo(134.34);
        assertThat(rate.get(CurrencyCode.TRY).doubleValue()).isEqualTo(18.61);
        assertThat(rate.get(CurrencyCode.GBP).doubleValue()).isEqualTo(0.8147);
    }

}
