package com.olmez.mya.springsecurity;

import java.rmi.UnexpectedException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.model.User;
import com.olmez.mya.springsecurity.config.SecurityConfig;
import com.olmez.mya.springsecurity.config.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authManager;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) throws UnexpectedException {

        User user = grantAuthentication(authRequest);
        if (user == null) {
            return ResponseEntity.status(400).body(SecurityConfig.EXCEPTION_MESSAGE + authRequest.getEmail());
        }
        String jwt = createJWTForUser(user);
        return ResponseEntity.ok(jwt);
    }

    private String createJWTForUser(User user) {
        return JwtUtils.generateToken(new UserDetailsImpl(user));
    }

    private User grantAuthentication(AuthRequest authRequest) throws UnexpectedException {
        // use email as username
        String username = authRequest.getEmail();
        String password = authRequest.getPassword();
        var aToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authResult = authManager.authenticate(aToken);
        return getPrincipalFromAuthentication(authResult);
    }

    private User getPrincipalFromAuthentication(Authentication authResult) throws UnexpectedException {
        Object principal = authResult.getPrincipal();
        if (!(principal instanceof UserDetailsImpl)) {
            throw new UnexpectedException(
                    String.format("Unexpected authentication principal:%s", principal.getClass().getName()));
        }
        return ((UserDetailsImpl) principal).getUser();
    }

}