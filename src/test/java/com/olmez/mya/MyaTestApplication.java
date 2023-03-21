package com.olmez.mya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.olmez.mya.utility.TestUtility;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Profile(TestUtility.PROFILE)
@Slf4j
public class MyaTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyaTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("-MYA Test application is running!");
    }

}
