package com.olmez.mya.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.mya.MyaApplicationTest;
import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.utility.TestSource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyaApplicationTest.class)
@TestPropertySource(TestSource.TEST_PROP_SOURCE)
@ActiveProfiles(TestSource.AC_PROFILE)
class ApiTokenServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepoCleanerService cleanerService;

    private User user;

    @BeforeEach
    void setup() {
        cleanerService.clear();

        user = new User("uname", "First", "Last");
        userRepository.save(user);
    }

    @Test
    public void testBasic() {
        assertThat(userRepository.findAll()).isNotEmpty();
    }

}
