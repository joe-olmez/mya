package com.olmez.mya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class MyaApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User appUser = userRepository.getAppUser();
		log.info("App user:{}", appUser);
		if (appUser == null) {
			log.info("Failed to connect to database! * * *");
		} else {
			log.info("* * * The database connection is successful! * * *");
			log.info("* * * Mya application has started! * * *");
		}
	}

}
