package com.olmez.mya.springsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.springsecurity.config.SecurityConfig;
import com.olmez.mya.springsecurity.config.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody AuthCredentials credentials) {

        // used email as username
        var authToken = authByUsernameAndPassword(credentials.getEmail(), credentials.getPassword());
        authManager.authenticate(authToken);

        User user = userRepository.findUserByEmail(credentials.getEmail());
        if (user != null) {
            String jwt = createTokenForUser(user);
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.status(400).body(SecurityConfig.EXCEPTION_MESSAGE + credentials.getEmail());
    }

    private String createTokenForUser(User user) {
        return JwtUtils.generateToken(new UserDetailsImpl(user));
    }

    private UsernamePasswordAuthenticationToken authByUsernameAndPassword(String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}