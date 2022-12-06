package com.olmez.mya.services.impl;

import com.olmez.mya.model.User;
import com.olmez.mya.model.enums.UserType;
import com.olmez.mya.repositories.UserRepository;
import com.olmez.mya.services.CurrentUserService;

public class DefaultUserServiceImpl implements CurrentUserService {

    private User user = new User();

    public DefaultUserServiceImpl() {
        this("Application");
    }

    public DefaultUserServiceImpl(User user) {
        this.user = user;
    }

    public DefaultUserServiceImpl(String applicationName) {
        user.setUsername(applicationName);
        user.setFirstName(applicationName);
        user.setLastName("System");
        user.setEmail(applicationName.toLowerCase() + "@emailtest.com");
        user.setUserType(UserType.APPLICATION);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    public void loadUserFromDB(UserRepository repository) {
        User temp = repository.getByUsername(user.getUsername());
        user = (temp == null) ? repository.save(user) : temp;
    }

}
