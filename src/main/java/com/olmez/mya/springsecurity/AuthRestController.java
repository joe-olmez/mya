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
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {

        var aToken = authByUsernameAndPassword(authRequest);
        authManager.authenticate(aToken);

        User user = userRepository.findUserByEmail(authRequest.getEmail());
        if (user != null) {
            String jwt = createTokenForUser(user);
            return ResponseEntity.ok(jwt);
        }
        return ResponseEntity.status(400).body(SecurityConfig.EXCEPTION_MESSAGE + authRequest.getEmail());
    }

    private String createTokenForUser(User user) {
        return JwtUtils.generateToken(new UserDetailsImpl(user));
    }

    private UsernamePasswordAuthenticationToken authByUsernameAndPassword(AuthRequest authRequest) {
        // use email as username
        String username = authRequest.getEmail();
        String password = authRequest.getPassword();
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}