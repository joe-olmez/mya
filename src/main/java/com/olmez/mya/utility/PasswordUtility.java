package com.olmez.mya.utility;

import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordUtility {

    // The higher the salt round value (sr), the more time the hashing algorithm
    // takes. It can be selected between 10 and 31.
    private static final int SALT_ROUND = 10;

    /**
     * @param pwInput is password as a plain text
     * @return A string of length 60 that is the bcrypt hashed password in crypt(3)
     *         format.
     */
    public static String hashPassword(String pwInput) {
        String salt = BCrypt.gensalt(SALT_ROUND);
        return BCrypt.hashpw(pwInput, salt);
    }

    /**
     * Compares the entered password with the hash in the database.
     * 
     * @param pwInput is password as a plain text
     * @param pwHash  The stored password hash in the database
     * @return {@code true} if the password matches the stored hash, {@code false}
     *         otherwise
     */
    public static boolean isMatched(String pwInput, String pwHash) {
        if (pwInput == null || pwHash == null) {
            throw new java.lang.IllegalArgumentException("Invalid input");
        }
        return BCrypt.checkpw(pwInput, pwHash);
    }

}
