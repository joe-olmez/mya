package com.olmez.mya.repositories;


import com.olmez.mya.MyaApplicationTest;
import com.olmez.mya.model.ApiToken;
import com.olmez.mya.model.User;
import com.olmez.mya.model.enums.UserType;
import com.olmez.mya.services.TestRepoCleanerService;
import com.olmez.mya.utility.TestSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Test classes use test database!
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyaApplicationTest.class)
@TestPropertySource(TestSource.TEST_PROP_SOURCE)
@ActiveProfiles(TestSource.AC_PROFILE)
class ApiTokenRepositoryTest {

    @Autowired
    private ApiTokenRepository apiTokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepoCleanerService cleanerService;

    private User user;
    private ApiToken apiToken;

    @BeforeEach
    void setup() {
        cleanerService.clear();

        user = new User("testUser", "test", "user", "testuser@email.com", UserType.REGULAR);
        user = userRepository.save(user);
        apiToken = new ApiToken();
        apiToken.setTokenHash(
                "844a13295d1b44856f504fcab0a45d8966c604903f77aa133e007acadf2eac62cd685123c188bf527c73b9b03d8376d9");
        apiToken.setTokenLabel("testToken");
        apiToken = apiTokenRepository.save(apiToken);
    }


}
