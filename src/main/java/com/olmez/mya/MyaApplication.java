package com.olmez.mya;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.mya.model.User;
import com.olmez.mya.model.securitydata.UserRoles;
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
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			userRepository.save(UserRoles.createTempUser());
		}
		log.info("*Database connection is OK! {} users", users.size());
		log.info("**Core application has started! * * *");

	}

}
