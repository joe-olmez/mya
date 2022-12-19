package com.olmez.mya.springsecurity.securityutiliy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;

}
