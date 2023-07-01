package com.olmez.mya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

import com.olmez.mya.utility.SourceUtils;

@SpringBootApplication
@TestPropertySource(SourceUtils.TEST_SOURCE)
public class MyaTestApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(MyaTestApplication.class, args);
	}

	@Bean
	@Profile(SourceUtils.TEST_PROFILE)
	CommandLineRunner loadData() {
		return args -> {
			log.info("Mya TEST application is running!");
		};
	}
}