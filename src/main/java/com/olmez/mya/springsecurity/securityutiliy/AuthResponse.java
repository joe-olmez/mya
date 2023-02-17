package com.olmez.mya.springsecurity.securityutiliy;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String errorMessage;

    public AuthResponse(String token, String errorMsg) {
        this.token = token;
        this.errorMessage = errorMsg;
    }

}
