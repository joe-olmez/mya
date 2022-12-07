package com.olmez.mya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.mya.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class MyaApplicationTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyaApplicationTest.class, args);
    }

    public void run(String... args) throws Exception {
        log.info("Mya Test application has started!");
    }

}
