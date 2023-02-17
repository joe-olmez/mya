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
import com.olmez.mya.utility.StringUtility;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;

    // Sign Up ********************************************
    public AuthResponse register(RegisterRequest request) {
        User user = checkExistingUser(request);
        if (user != null) {
            var errMsg = String.format("Error: %s is already in use!", user.getName());
            return new AuthResponse(null, errMsg);
        }
        createNewUser(request);
        return new AuthResponse(null, null);
    }

    // Sign In *************** Generate a token for the user to login***************
    public AuthResponse authenticate(AuthRequest request) throws UnexpectedException {
        UserDetailsImpl userDetailsImpl = grantAuthentication(request);
        User user = userDetailsImpl.getUser();
        String errorMsg = null;
        if (user == null) {
            errorMsg = "User not found with " + request.getUsername();
            return new AuthResponse(null, errorMsg);
        }
        String jwt = createJWTForUser(userDetailsImpl);
        log.info("Granted jwt:{} to {}", jwt, user.getName());
        return new AuthResponse(jwt, null);
    }

    //
    private User checkExistingUser(RegisterRequest request) {
        if (request.getEmail() != null) {
            var oUser = userRepository.findByEmail(request.getEmail());
            return oUser.isPresent() ? oUser.get() : null;
        }
        if (request.getUsername() != null) {
            return userRepository.findByUsername(request.getUsername());
        }
        return null;
    }

    private void createNewUser(RegisterRequest request) {
        User user = new User(request.getFirstName(), request.getLastName(), request.getUsername(), request.getEmail());
        user.setPasswordHash(PasswordUtility.hashPassword(request.getPassword()));
        userRepository.save(user);
    }

    private UserDetailsImpl grantAuthentication(AuthRequest signinRequest) throws UnexpectedException {
        String username = signinRequest.getUsername();
        if (StringUtility.isEmpty(username)) {
            username = signinRequest.getEmail();
        }
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

}
