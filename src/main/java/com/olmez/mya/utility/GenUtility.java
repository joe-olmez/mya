package com.olmez.mya.utility;

import java.security.SecureRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GenUtility {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String NAME_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private SecureRandom random = new SecureRandom();

    public String generateName(int length) {
        if (length < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(NAME_ALLOW_BASE.length());
            char rndChar = NAME_ALLOW_BASE.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

}