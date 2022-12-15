package com.olmez.mya.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.olmez.mya.MyaApplicationTest;
import com.olmez.mya.model.User;
import com.olmez.mya.services.TestRepoCleanerService;

import static org.assertj.core.api.Assertions.assertThat;
import com.olmez.mya.utility.TestSource;

/**
 * Test classes use test database!
 */
@SpringBootTest(classes = MyaApplicationTest.class)
@TestPropertySource(TestSource.TEST_PROP_SOURCE)
@ActiveProfiles(TestSource.AC_PROFILE)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    @Autowired
    private TestRepoCleanerService cleanerService;

    @BeforeEach
    public void setup() {
        cleanerService.clear();
    }

    @Test
    void testFindByUsername() {
        // arrange
        User user = new User("uname", "First", "Last");
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
        User user1 = new User("uname", "First", "Last");
        user1 = repository.save(user1);
        User user2 = new User("uname2", "First2", "Last2");
        user2 = repository.save(user2);

        // act
        var user = repository.findByUsername(user1.getUsername());

        // assert
        assertThat(user).isNotNull().isEqualTo(user1);
    }

}
