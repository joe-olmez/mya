package com.olmez.mya.services.impl;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olmez.mya.model.ApiToken;
import com.olmez.mya.model.User;
import com.olmez.mya.repositories.ApiTokenRepository;
import com.olmez.mya.utility.StringUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiTokenServiceImpl implements ApiTokenService {
    private final ApiTokenRepository apiTokenRepository;
    private final KeyGenerator keyGenerator;

    @Override
    @Transactional
    public boolean createApiTokenForUser(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }

        ApiToken apiToken = new ApiToken();
        apiToken.setTokenHash(generateSecureToken());
        apiToken.setTokenLabel(user.getName());
        apiToken.setUser(user);
        apiTokenRepository.save(apiToken);
        return true;
    }

    private String generateSecureToken() {
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(secretKey.getEncoded());
    }

    @Override
    @Transactional
    public User authenticateApiToken(String tokenHash) {
        if (StringUtility.isEmpty(tokenHash)) {
            return null;
        }
        ApiToken token = apiTokenRepository.getApiTokenByTokenHash(tokenHash);
        return token != null ? token.getUser() : null;
    }

}
