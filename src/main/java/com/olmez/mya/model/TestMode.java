package com.olmez.mya.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestMode {
    private boolean test;
    private String resource = "";

    public TestMode(boolean mode, String resource) {
        this.test = mode;
        this.resource = resource;
    }

    public String toString() {
        return resource;
    }
}
