package com.olmez.mya.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.mya.model.CurrencyRate;
import com.olmez.mya.services.TestRepoCleanerService;
import com.olmez.mya.utility.TestUtility;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class CurrencyInfoRepositoryTest {

	@Autowired
	private CurrencyRateRepository repository;
	@Autowired
	private TestRepoCleanerService cleanerService;

	@BeforeEach
	public void setup() {
		cleanerService.clear();
	}

	@Test
	void testFindAll() {
		// arrange
		var rate = new CurrencyRate();
		rate.setDate(LocalDate.of(2023, 2, 13));
		rate = repository.save(rate);

		var rate2 = new CurrencyRate();
		rate2.setDate(LocalDate.of(2023, 2, 14));
		rate2 = repository.save(rate2);

		var rate3 = new CurrencyRate();
		rate3.setDate(LocalDate.of(2023, 2, 15));
		rate3 = repository.save(rate3);

		// act
		var rates = repository.findAll();

		// assert
		assertThat(rates).hasSize(3);
		assertThat(rates.get(0)).isEqualTo(rate3); // Feb 15
		assertThat(rates.get(1)).isEqualTo(rate2); // Feb 14
		assertThat(rates.get(2)).isEqualTo(rate);// Feb 13

	}

}