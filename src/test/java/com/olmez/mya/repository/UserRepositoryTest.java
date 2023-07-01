package com.olmez.mya.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.olmez.mya.MyaTestApplication;
import com.olmez.mya.model.User;
import com.olmez.mya.utility.SourceUtils;

@SpringBootTest(classes = MyaTestApplication.class)
@ActiveProfiles(SourceUtils.TEST_PROFILE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user = new User("First", "Last", "uname", "email");
    private User user2 = new User("First2", "Last2", "uname2", "email2");

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void testFindAll() {
        // act
        var list = userRepository.findAll();
        // assert
        assertThat(list).isEmpty();
    }

    @Test
    void testFindByUsername() {
        // arrange
        user = userRepository.save(user);
        // act
        var users = userRepository.findUsersByUsername(user.getUsername());
        // assert
        assertThat(users).hasSize(1).contains(user);
    }

    @Test
    void testGetByUsername() {
        // arrange
        user = userRepository.save(user);
        user2 = userRepository.save(user2);
        // act
        var resUser = userRepository.findByUsername(user.getUsername());
        // assert
        assertThat(user).isNotNull().isEqualTo(resUser);
    }

}
