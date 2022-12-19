package com.olmez.mya.springsecurity;

import java.rmi.UnexpectedException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.springsecurity.config.SecurityConfig;
import com.olmez.mya.springsecurity.config.UserDetailsImpl;
import com.olmez.mya.springsecurity.securityutiliy.AuthRequest;
import com.olmez.mya.springsecurity.securityutiliy.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authManager;

    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) throws UnexpectedException {

        UserDetailsImpl userDetailsImpl = grantAuthentication(authRequest);
        if (userDetailsImpl.getUser() == null) {
            return ResponseEntity.status(400).body(SecurityConfig.EXCEPTION_MESSAGE + authRequest.getUsername());
        }
        String jwt = createJWTForUser(userDetailsImpl);
        return ResponseEntity.ok(jwt);
    }

    private UserDetailsImpl grantAuthentication(AuthRequest authRequest) throws UnexpectedException {
        // use email as username
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        var aToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authResult = authManager.authenticate(aToken);
        return getPrincipalFromAuthentication(authResult);
    }

    private UserDetailsImpl getPrincipalFromAuthentication(Authentication authResult) throws UnexpectedException {
        Object principal = authResult.getPrincipal();
        if (!(principal instanceof UserDetailsImpl)) {
            throw new UnexpectedException(
                    String.format("Unexpected authentication principal:%s", principal.getClass().getName()));
        }
        return (UserDetailsImpl) principal;
    }

    private String createJWTForUser(UserDetails userDetails) {
        return JwtUtils.generateToken(userDetails);
    }

}