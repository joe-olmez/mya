package com.olmez.mya.currency;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.mya.repositories.CurrencyInfoRepository;

@ExtendWith(MockitoExtension.class)
class CurrencyAPIServiceImplTest {

    @InjectMocks
    private CurrencyAPIServiceImpl apiService;
    @Mock
    private CurrencyInfoRepository currencyInfoRepository;

    private String jsonResource = "/currency/rates.json";

    @Test
    void testUpdate_No_Date() throws IOException, InterruptedException {
        // arrange
        apiService.setTestMode(true);
        apiService.setTestResource(jsonResource);

        // act
        var retVal = apiService.update();

        // assert
        assertThat(retVal).isNotNull();
    }

    @Test
    void testUpdate_With_Date() throws IOException, InterruptedException {
        // arrange
        apiService.setTestMode(true);
        apiService.setTestResource(jsonResource);

        var date = LocalDate.of(2022, 12, 6);

        // act
        var retVal = apiService.update(date);

        // assert
        assertThat(retVal.getCAD()).isEqualTo(1.3468);
        assertThat(retVal.getJPY()).isEqualTo(134.34);
        assertThat(retVal.getTRY()).isEqualTo(18.61);
        assertThat(retVal.getGBP()).isEqualTo(0.8147);
    }

}
