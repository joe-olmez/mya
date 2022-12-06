package com.olmez.mya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.services.impl.DefaultUserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class MyaApplicationTest implements CommandLineRunner  {

    private final UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(MyaApplicationTest.class, args);
    }
    
    
    
    public void run(String... args) throws Exception {
        DefaultUserServiceImpl currentUser = new DefaultUserServiceImpl("Test");
		currentUser.loadUserFromDB(userRepository);
		log.info("Test app has started!");
    }
    
}
