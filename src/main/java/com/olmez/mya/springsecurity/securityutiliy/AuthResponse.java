package com.olmez.mya.springsecurity.securityutiliy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String username;
    private String email;
    private String role;
    private String errorMessage;

}
