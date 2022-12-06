package com.olmez.mya.services;

import java.time.ZoneId;
import java.time.ZoneOffset;

import com.olmez.mya.model.User;

public interface CurrentUserService {

    User getUser();

    void setUser(User user);

    default ZoneId getZoneId() {
        return hasUser() ? ZoneId.of(getUser().getTimeZone()) : ZoneOffset.UTC;
    }

    default boolean hasUser() {
        return getUser() != null;
    }

}
