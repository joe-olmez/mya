package com.olmez.mya.springsecurity.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.olmez.mya.model.User;

import lombok.Getter;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String passwordHash;
    private String role;
    @Getter
    private User curUser;

    public UserDetailsImpl(User user) {
        this.curUser = user;
        this.username = user.getUsername();
        this.passwordHash = user.getPasswordHash();
        this.role = user.getUserType().getRole().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role);
        return Collections.singletonList(auth);
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
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
