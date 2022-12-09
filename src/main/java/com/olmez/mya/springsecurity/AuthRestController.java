package com.olmez.mya.springsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.config.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticateActionRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails user = userDao.findUserByEmail(request.getEmail());
        if (user != null) {
            String tok = JwtUtils.generateToken(user);
            return ResponseEntity.ok(tok);
        }
        return ResponseEntity.status(400).body("Oops! Some error has occurred!");

    }

}