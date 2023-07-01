package com.olmez.mya;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.olmez.mya.currency.CurrencyService;
import com.olmez.mya.model.User;
import com.olmez.mya.repository.UserRepository;
import com.olmez.mya.utility.SourceUtils;

@SpringBootApplication
@EnableScheduling
public class MyaApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SourceUtils.setSpringConfigLocation();
		SpringApplication.run(MyaApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(UserRepository userRepository, CurrencyService service) {
		return args -> {
			log.info("Loading data");
			log.info("Checking for Users from Database");
			List<User> users = userRepository.findAll();
			log.info("Checked users (size:{}) from Database, yeah!", users.size());
			log.info("Mya application is running!");
			// service.checkLastWeek();
		};
	}
}