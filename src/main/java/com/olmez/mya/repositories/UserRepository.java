package com.olmez.mya.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.olmez.mya.model.User;
import com.olmez.mya.utility.StringUtility;

public interface UserRepository extends BaseObjectRepository<User> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.deleted = false")
    List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.deleted = false")
    Optional<User> findByEmail(String email);

    default User getByUsername(String username) {
        if (StringUtility.isEmpty(username)) {
            return null;
        }

        List<User> users = findByUsername(username);
        if (users.isEmpty()) {
            return null;
        }

        User user = users.get(0);
        if (users.size() > 1) {
            users.forEach(u -> u.setDeleted(true));
        }
        return user;
    }

    default User findUserByEmail(String email) {
        var oUser = findByEmail(email);
        if (oUser.isPresent()) {
            return oUser.get();
        } else {
            throw new UsernameNotFoundException("No user was found!");
        }
    }
}
