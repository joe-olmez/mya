package com.olmez.mya.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.olmez.mya.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String EXCEPTION_MESSAGE = "User Not Found: ";

    private final com.olmez.mya.repositories.UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException(EXCEPTION_MESSAGE + userName);
        }

        return new UserDetailsImpl(user);
    }
}
