package com.olmez.mya.springsecurity.securityutiliy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String email;
    private String password;

}
