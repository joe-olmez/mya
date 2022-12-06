package com.olmez.mya.security.authentication;

import com.olmez.mya.model.User;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class AuthenticationContextImpl implements AuthenticationContext {

    User user;

}
