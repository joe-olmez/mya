package com.olmez.mya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olmez.mya.model.User;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean addUser(User newUser) {
        if (newUser == null) {
            return false;
        }
        newUser = userRepository.save(newUser);
        return newUser.getId() != null;
    }

    @Override
    @Transactional
    public User getUserById(Long userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.getById(userId);
    }

    @Override
    @Transactional
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
    @Transactional
    public User updateUser(Long id, User givenUser) {
        User existing = getUserById(id);
        if (existing == null) {
            return null;
        }

        copy(givenUser, existing);
        userRepository.save(existing);
        log.info("Updated! {}", existing);
        return existing;
    }

    private User copy(User source, User target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setUserType(source.getUserType());
        target.setPasswordHash(source.getPasswordHash());
        target.setTimeZone(source.getTimeZone());
        return target;
    }

}
