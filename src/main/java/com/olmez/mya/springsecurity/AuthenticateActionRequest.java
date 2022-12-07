package com.olmez.mya.springsecurity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticateActionRequest {

    private String email;
    private String password;

}
