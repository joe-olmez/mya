package com.olmez.mya.repositories;

import com.olmez.mya.MyaApplicationTest;
import com.olmez.mya.model.CurrencyInfo;
import com.olmez.mya.services.TestRepoCleanerService;


import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.olmez.mya.utility.TestSource;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test classes use test database!
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyaApplicationTest.class)
@TestPropertySource(TestSource.TEST_PROP_SOURCE)
@ActiveProfiles(TestSource.AC_PROFILE)
class CurrencyInfoRepositoryTest {

	@Autowired
	private CurrencyInfoRepository repository;
	@Autowired
	private TestRepoCleanerService cleanerService;

	@BeforeEach
	public void setup() {
		cleanerService.clear();
	}

	@Test
	void testFindAll() {
		// arrange
		var date = LocalDate.of(2022, 12, 3);
		var info = new CurrencyInfo(date);
		info = repository.save(info);

		var info2 = new CurrencyInfo(LocalDate.of(2022, 12, 4));
		info2 = repository.save(info2);

		var info3 = new CurrencyInfo(LocalDate.of(2022, 12, 2));
		info3 = repository.save(info3);

		// act
		var infos = repository.findAll();

		// assert
		assertThat(infos).hasSize(3);
		assertThat(infos.get(0)).isEqualTo(info2); //Dec 4
		assertThat(infos.get(1)).isEqualTo(info); //Dec 3
		assertThat(infos.get(2)).isEqualTo(info3); //Dec 2

	}

}
