package com.olmez.mya.springsecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.springsecurity.config.SecurityConfig;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        // second after auth
        User curUser = userRepository.findUserByEmail(authRequest.getEmail());
        if (curUser == null) {
            return ResponseEntity.status(400).body(SecurityConfig.EXCEPTION_MESSAGE + authRequest.getEmail());
        }
        UserDetails appUser = new UserDetailsImpl(curUser);
        String userToken = JwtUtils.generateToken(appUser);
        return ResponseEntity.ok(userToken);
    }

}