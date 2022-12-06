package com.olmez.mya.services.impl;

import com.olmez.mya.model.User;

public interface ApiTokenService {

    boolean createApiTokenForUser(User user);

    User authenticateApiToken(String tokenHash);
}
