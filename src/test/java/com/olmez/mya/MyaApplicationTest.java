package com.olmez.mya;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        log.info("Mya TEST application has started!");
    }

    @Test
    void testBasic() {
        var text = "text";
        assertThat(text).isEqualTo("text");
    }
}
