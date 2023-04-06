package com.olmez.mya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import com.olmez.mya.utility.TestUtility;

@SpringBootApplication
@TestPropertySource(TestUtility.TEST_SOURCE)
public class MyaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyaTestApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            System.out.println();
        };
    }

    // @Bean
    // @Profile(TestUtility.TEST_PROFILE)
    // public DataSource dataSource() {
    // return new EmbeddedDatabaseBuilder()
    // .setType(EmbeddedDatabaseType.H2)
    // .addScript("classpath:db/schema.sql")
    // .addScript("classpath:db/data.sql")
    // .build();
    // }
}
