package com.olmez.mya;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public static void main(String[] args) {
		SpringApplication.run(MyaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<User> users = userRepository.findAll();
		User curUser = null;
		if (users.isEmpty()) {
			curUser = new User("joe", "joseph", "olmez", "joe.olmez@gmail.com", UserType.ADMIN);
			curUser.setPasswordHash("1234");
			curUser = userRepository.save(curUser);
		}
		log.info("Current user:{}", curUser);
		log.info("* * * The database connection is successful! * * *");
		log.info("* * * Mya application has started! * * *");
	}
}
