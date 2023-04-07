package com.olmez.mya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.olmez.mya.repo.UserRepository;

@SpringBootApplication
@EnableScheduling
public class MyaApplication {

	@Autowired
	private UserRepository repository;

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(MyaApplication.class, args);
	}

	public CommandLineRunner loadData() {
		return args -> {
			log.info("Mya application is running!");
			var list = repository.findAll();
			log.info("Database connection is OK! users:{}", list.size());
		};
	}
}