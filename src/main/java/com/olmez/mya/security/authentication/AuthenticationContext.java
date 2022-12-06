package com.olmez.mya.security.authentication;

import com.olmez.mya.model.User;

/**
 * This allows getting the user that is in the context related to a request.
 */
public interface AuthenticationContext {

    // Gives user in the context
    User getUser();
}
