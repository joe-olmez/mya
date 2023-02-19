package com.olmez.mya.model;

import lombok.Data;

@Data
public class PasswordWrapper {
    private String username;
    private String rawPassword;
}
