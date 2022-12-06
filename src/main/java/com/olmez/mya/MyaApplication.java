package com.olmez.mya;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.mya.currency.CurrencyService;
import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class MyaApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CurrencyService service;

	public static void main(String[] args) {
		SpringApplication.run(MyaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<User> users = userRepository.findAll();

		log.info("Number of users in DB:" + users.size());
		log.info("* * * The database connection is successful! * * *");
		log.info("* * * Myapp has started! * * *");

		service.update(LocalDate.of(2022, 11, 1), LocalDate.of(2022, 12, 15));
		System.out.println();
	}
}
