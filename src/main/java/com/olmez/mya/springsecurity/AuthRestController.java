package com.olmez.mya.springsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody AuthActionInfo actionInfo) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(actionInfo.getEmail(), actionInfo.getPassword()));

        UserDetails user = userDao.findUserByEmail(actionInfo.getEmail());
        if (user != null) {
            String userToken = JwtUtils.generateToken(user);
            return ResponseEntity.ok(userToken);
        }
        return ResponseEntity.status(400).body("Oops! Some error has occurred!");

    }

}