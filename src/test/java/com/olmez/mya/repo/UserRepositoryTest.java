package com.olmez.mya.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.mya.MyaTestApplication;
import com.olmez.mya.model.User;
import com.olmez.mya.repository.UserRepository;
import com.olmez.mya.utility.TestUtility;

@SpringBootTest(classes = MyaTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user = new User("First", "Last", "uname", "email");
    private User user2 = new User("First2", "Last2", "uname2", "email2");

    @AfterEach
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
        assertThat(users).hasSize(1);
        assertThat(users.get(0)).isEqualTo(user);
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
