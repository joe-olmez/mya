package com.olmez.mya.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.olmez.mya.model.PasswordWrapper;
import com.olmez.mya.model.User;
import com.olmez.mya.repo.UserRepository;
import com.olmez.mya.services.UserService;
import com.olmez.mya.springsecurity.config.UserDetailsImpl;
import com.olmez.mya.springsecurity.securityutiliy.PasswordUtility;
import com.olmez.mya.utility.StringUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean addUser(User newUser) {
        if (newUser == null) {
            return false;
        }
        newUser = userRepository.save(newUser);
        return newUser.getId() != null;
    }

    @Override
    public User getUserById(Long userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.getById(userId);
    }

    @Override
    public boolean deleteUser(Long userId) {
        User existing = getUserById(userId);
        if (existing == null) {
            return false;
        }
        userRepository.deleted(existing);
        log.info("Deleted {}", existing);
        return true;
    }

    @Override
    public User getUserByUsername(String username) {
        if (StringUtility.isEmpty(username)) {
            return null;
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(Long id, User givenUser) {
        User existing = getUserById(id);
        if (existing == null || givenUser == null) {
            return null;
        }
        return copy(givenUser, existing);
    }

    @Override
    public User updateUser(User givenUser) {
        User existing = getUserById(givenUser.getId());
        if (existing == null) {
            return null;
        }
        return copy(givenUser, existing);
    }

    private User copy(User source, User target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setEmail(source.getEmail());
        target.setUsername(source.getUsername());
        target = userRepository.save(target);
        log.info("Updated! {}", target);
        return target;
    }

    @Override
    public User getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }

        try {
            var udi = (UserDetailsImpl) auth.getPrincipal();
            return udi.getUser();
        } catch (Exception e) {
            log.info("Failed to find current user! {}", e);
            return null;
        }
    }

    @Override
    public boolean updateUserPassword(PasswordWrapper passWrapper) {
        if (passWrapper == null) {
            return false;
        }

        String username = passWrapper.getUsername();
        String rawPassword = passWrapper.getRawPassword();
        User existing = getUserByUsername(username);

        if (existing == null || StringUtility.isEmpty(rawPassword)) {
            return false;
        }
        existing.setPasswordHash(PasswordUtility.hashPassword(rawPassword));
        return true;
    }

}
