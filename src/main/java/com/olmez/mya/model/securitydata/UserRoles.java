package com.olmez.mya.model.securitydata;

import com.olmez.mya.model.User;
import com.olmez.mya.model.enums.UserType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserRoles {

    /**
     * Represents the role of admin users.
     */
    public static final String ROLE_ADMIN = "Admin";
    /**
     * Represents the role of regular users.
     */
    public static final String ROLE_USER = "User";

    public static final String ROLE_APP = "Application";

    public static final String ROLE_TEAM = "Team";

    public static User createTempUser() {
        return new User("First", "Last", "appUser", "app@user.com", UserType.APPLICATION);
    }

}
