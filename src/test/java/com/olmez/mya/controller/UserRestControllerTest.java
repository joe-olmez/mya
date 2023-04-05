package com.olmez.mya.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.olmez.mya.model.User;
import com.olmez.mya.services.UserService;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest extends BaseTestRestController {

    @InjectMocks
    private UserRestController controller;
    @Mock
    private UserService userService;

    private User user = new User("First", "Last", "uname", "email");
    private User user2 = new User("First2", "Last2", "uname2", "email2");

    @BeforeEach
    void setup() {
        doMockRequest();
    }

    @Test
    void testGetAllUsers() {
        // adjust
        when(userService.getUsers()).thenReturn(List.of(user, user2));
        // act
        var resEntity = controller.getAllUsers();
        // assert
        assertThat(resEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        var body = resEntity.getBody();
        assertThat(body).hasSize(2).contains(user, user2);
    }

    @Test
    void testAddUser() {
        // adjust
        when(userService.addUser(any(User.class))).thenReturn(true);
        // act
        User newUser = new User("NewName", "NewLast", "NewUname", "new@email.com");
        var resEntity = controller.addUser(newUser);
        // assert
        assertThat(resEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

}
