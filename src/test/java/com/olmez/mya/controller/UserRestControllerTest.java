package com.olmez.mya.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;

import com.olmez.mya.MyaTestApplication;
import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.services.TestRepoCleanerService;
import com.olmez.mya.utility.TestUtility;

/**
 * Test classes use test database!
 */
@SpringBootTest(classes = MyaTestApplication.class)
@TestPropertySource(TestUtility.SOURCE_PROPERTIES)
@ActiveProfiles(TestUtility.PROFILE)
@AutoConfigureMockMvc
class UserRestControllerTest extends BaseTestRestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepoCleanerService cleanerService;

    @BeforeEach
    void setup() {
        cleanerService.clear();
    }

    @Test
    void testGetAllUsers_NoAuth_Expected_403() throws Exception {
        // adjust
        User user = new User("First", "Last", "uname", "email");
        user = userRepository.save(user);
        User user2 = new User("First2", "Last2", "uname2", "email2");
        user2 = userRepository.save(user2);

        // act
        MvcResult result = doMockRequest("/api/v1/users", HttpMethod.GET);
        int res = getResponseStatus(result);
        String context = getResponseAsString(result);

        // assert
        assertThat(res).isEqualTo(403);
        assertThat(context).isEmpty();
    }

}
