package com.olmez.mya;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.olmez.mya.utility.TestUtility;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Profile(TestUtility.PROFILE)
@Slf4j
public class MyaApplicationTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyaApplicationTest.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("---Test application is running!--");
    }

    @Test
    void testBasic() {
        assertThat(TestUtility.PROFILE).isEqualTo("test");
    }

}
