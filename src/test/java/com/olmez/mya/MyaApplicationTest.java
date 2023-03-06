package com.olmez.mya;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyaApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(MyaApplicationTest.class, args);
    }

    @Test
    void testBasic() {
        var text = "text";
        assertThat(text).isEqualTo("text");
    }
}
