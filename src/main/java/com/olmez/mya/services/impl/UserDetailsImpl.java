package com.olmez.mya.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.olmez.mya.model.User;

import lombok.Getter;

public class UserDetailsImpl implements UserDetails {

    private final String userName;
    private final String passwordHash;
    @Getter
    private final Collection<? extends GrantedAuthority> authorities;
    @Getter
    private transient User user;

    public UserDetailsImpl(User user) {
        this.user = user;
        this.userName = user.getUsername();
        this.passwordHash = user.getPasswordHash();
        this.authorities = user.getUserType().getRole().map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
