package com.olmez.mya.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.olmez.mya.MyaApplicationTest;
import com.olmez.mya.model.User;
import com.olmez.mya.services.TestRepoCleanerService;
import com.olmez.mya.utility.TestUtility;

/**
 * Test classes use test database!
 */
@SpringBootTest(classes = MyaApplicationTest.class)
@TestPropertySource(TestUtility.SOURCE_PROPERTIES)
@ActiveProfiles(TestUtility.PROFILE)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    @Autowired
    private TestRepoCleanerService cleanerService;

    private User user = new User("First", "Last", "uname", "email");
    private User user2 = new User("First2", "Last2", "uname2", "email2");

    @BeforeEach
    public void setup() {
        cleanerService.clear();
    }

    @Test
    void testFindByUsername() {
        // arrange
        user = repository.save(user);

        // act
        var users = repository.findUsersByUsername(user.getUsername());

        // assert
        assertThat(users).hasSize(1);
        assertThat(users.get(0)).isEqualTo(user);
    }

    @Test
    void testGetByUsername() {
        // arrange
        user = repository.save(user);
        user2 = repository.save(user2);

        // act
        var resUser = repository.findByUsername(user.getUsername());

        // assert
        assertThat(user).isNotNull().isEqualTo(resUser);
    }

}
