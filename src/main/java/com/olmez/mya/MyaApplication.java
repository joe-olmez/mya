package com.olmez.mya;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.mya.currency.CurrencyService;
import com.olmez.mya.model.User;
import com.olmez.mya.model.enums.UserType;
import com.olmez.mya.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class MyaApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CurrencyService currencyService;

	public static void main(String[] args) {
		SpringApplication.run(MyaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User curUser = checkUser();
		log.info("Current user:{}", curUser);
		log.info("* * * The database connection is successful! * * *");
		log.info("* * * Mya application has started! * * *");

		testCurrencyService();
	}

	private User checkUser() {
		User user = userRepository.findUserByEmail("joe.olmez@gmail.com");
		if (user != null) {
			return user;
		}
		user = new User("joe", "joseph", "olmez", "joe.olmez@gmail.com", UserType.ADMIN);
		user.setPasswordHash("1234");
		return userRepository.save(user);
	}

	private void testCurrencyService() throws IOException, InterruptedException {
		// currencyService.update(LocalDate.of(2022, 8, 6), LocalDate.of(2022, 11, 6));
	}
}
