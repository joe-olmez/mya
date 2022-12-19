package com.olmez.mya.springsecurity.securityutiliy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SigninRequest {

    private String username;
    private String email;
    private String password;

}
