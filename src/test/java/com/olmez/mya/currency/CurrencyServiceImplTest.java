package com.olmez.mya.currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.mya.model.CurrencyRate;
import com.olmez.mya.model.TestMode;
import com.olmez.mya.model.enums.CurrencyCode;
import com.olmez.mya.repo.CurrencyRateRepository;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl service;
    @Spy
    private CurrencyAPIServiceImpl apiService = new CurrencyAPIServiceImpl();
    @Mock
    private CurrencyRateRepository repository;

    private TestMode testMode = new TestMode(true, "/currency/rates.json");

    @Test
    void testUpdate_With_Date() throws IOException, InterruptedException {
        // arrange
        apiService.setTestMode(testMode);
        var date = LocalDate.of(2022, 12, 6);

        // act
        var retVal = apiService.update(date);

        // assert
        assertThat(retVal.getCad()).isEqualTo(1.3468);
        assertThat(retVal.getJpy()).isEqualTo(134.34);
        assertThat(retVal.getTryy()).isEqualTo(18.61);
        assertThat(retVal.getGbp()).isEqualTo(0.8147);
    }

    @Test
    void testConvert_USD_TRY() throws InterruptedException, IOException {
        // arrange
        var date = LocalDate.of(2022, 12, 6);
        CurrencyWrapper curWrapper = new CurrencyWrapper(date, 100d, CurrencyCode.USD, CurrencyCode.TRY);

        var rate = new CurrencyRate(date, 1.35, 0.95, 0.8, 135.5, 18.85, 1d);
        when(repository.findByDate(date)).thenReturn(rate);

        // act
        var result = service.convert(curWrapper);

        // assert
        assertThat(result.intValue()).isEqualTo(1885); // USD->TRY
    }

    @Test
    void testConvert_CAD_TRY() throws InterruptedException, IOException {
        // arrange
        var date = LocalDate.of(2022, 12, 6);
        CurrencyWrapper curWrapper = new CurrencyWrapper(date, 100d, CurrencyCode.CAD, CurrencyCode.TRY);

        var rate = new CurrencyRate(date, 1.35, 0.95, 0.8, 135.5, 18.85, 1d);
        when(repository.findByDate(date)).thenReturn(rate);

        // act
        var result = service.convert(curWrapper);

        // assert
        assertThat(result.intValue()).isEqualTo(1396); // CAD->TRY
    }

}
