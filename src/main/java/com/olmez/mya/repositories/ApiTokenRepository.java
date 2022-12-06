package com.olmez.mya.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.olmez.mya.model.ApiToken;
import com.olmez.mya.model.User;

public interface ApiTokenRepository extends BaseObjectRepository<ApiToken> {

    @Query("SELECT t FROM ApiToken t WHERE t.tokenHash = ?1 AND t.deleted = false")
    Optional<ApiToken> findByTokenHash(String tokenHash);

    default ApiToken getApiTokenByTokenHash(String tokenHash) {
        Optional<ApiToken> oToken = findByTokenHash(tokenHash);
        return oToken.isPresent() ? oToken.get() : null;
    }

    @Query("SELECT t FROM ApiToken t WHERE t.user = ?1 AND t.deleted=false AND t.user.deleted=false")
    Optional<ApiToken> findByUser(User user);

    default ApiToken getApiTokenByUser(User user) {
        Optional<ApiToken> oToken = findByUser(user);
        return oToken.isPresent() ? oToken.get() : null;
    }

}
