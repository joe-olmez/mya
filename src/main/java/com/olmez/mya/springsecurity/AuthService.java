package com.olmez.mya.springsecurity;

import java.rmi.UnexpectedException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.springsecurity.config.UserDetailsImpl;
import com.olmez.mya.springsecurity.securityutiliy.AuthRequest;
import com.olmez.mya.springsecurity.securityutiliy.AuthResponse;
import com.olmez.mya.springsecurity.securityutiliy.JwtUtility;
import com.olmez.mya.springsecurity.securityutiliy.PasswordUtility;
import com.olmez.mya.springsecurity.securityutiliy.RegisterRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
        String errorMsg = null;
        User user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            errorMsg = String.format("Error: %s is already in use!", request.getUsername());
        }
        // Create a new user's account
        user = new User(request.getUsername(), request.getFirstName(), request.getLastName(),
                request.getEmail());
        user.setPasswordHash(PasswordUtility.hashPassword(request.getPassword()));
        user = userRepository.save(user);
        return generateResponse(user, null, errorMsg);
    }

    public AuthResponse authenticate(AuthRequest request) throws UnexpectedException {
        UserDetailsImpl userDetailsImpl = grantAuthentication(request);
        User curUser = userDetailsImpl.getUser();
        String errorMsg = null;
        if (curUser == null) {
            errorMsg = "User not found with " + request.getUsername();
        }
        String jwt = createJWTForUser(userDetailsImpl);
        log.info("Granted jwt:{} to user:{}", jwt, curUser);
        return generateResponse(curUser, jwt, errorMsg);
    }

    private UserDetailsImpl grantAuthentication(AuthRequest signinRequest) throws UnexpectedException {
        String username = signinRequest.getUsername();
        String password = signinRequest.getPassword();
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
        return JwtUtility.generateToken(userDetails);
    }

    private AuthResponse generateResponse(User user, String token, String error) {
        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setErrorMessage(error);
        if (user != null) {
            res.setUsername(user.getUsername());
            res.setEmail(user.getEmail());
            res.setRole(user.getRole());
        }
        return res;
    }

}
