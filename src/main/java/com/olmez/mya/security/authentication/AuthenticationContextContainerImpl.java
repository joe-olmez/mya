package com.olmez.mya.security.authentication;

import java.util.Optional;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthenticationContextContainerImpl implements AuthenticationContextContainer {

    private @Nullable AuthenticationContext authContext;

    @Override
    public void setContext(@Nullable AuthenticationContext authContext) {
        this.authContext = authContext;

    }

    @Override
    public Optional<AuthenticationContext> getContext() {
        return Optional.ofNullable(authContext);
    }

}
