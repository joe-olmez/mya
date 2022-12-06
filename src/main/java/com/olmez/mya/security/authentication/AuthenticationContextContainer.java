package com.olmez.mya.security.authentication;

import java.util.Optional;

/**
 * Context is created for each request in {@link SecurityInterceptor}. This
 * keeps this generated context.
 */
public interface AuthenticationContextContainer {

    /**
     * This sets the given context to the context container
     * 
     * @param context AuthenticationContext
     */
    void setContext(AuthenticationContext context);

    /**
     * Gets the current context in the context container.
     * 
     * @return AuthenticationContext if it is available, otherwise empty.
     */
    Optional<AuthenticationContext> getContext();

}
