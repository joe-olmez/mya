package com.olmez.mya.currency;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.mya.model.TestMode;
import com.olmez.mya.repository.CurrencyRateRepository;

@ExtendWith(MockitoExtension.class)
class CurrencyAPIServiceImplTest {

    @InjectMocks
    private CurrencyAPIServiceImpl apiService;
    @Mock
    private CurrencyRateRepository currencyInfoRepository;

    private TestMode testMode = new TestMode(true, "/currency/rates.json");

    @Test
    void testUpdate_No_Date() throws IOException, InterruptedException {
        // arrange
        apiService.setTestMode(testMode);

        // act
        var retVal = apiService.update();

        // assert
        assertThat(retVal).isNotNull();
    }

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

}
