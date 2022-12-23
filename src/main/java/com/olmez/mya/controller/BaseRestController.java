package com.olmez.mya.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseRestController {

    protected void returnError(HttpServletResponse response, String message) throws IOException {
        returnError(response, message, null);
    }

    protected void returnError(HttpServletResponse response, String message, Exception e) throws IOException {
        if (e != null) {
            log.error(message, e);
        } else {
            log.error(message);
        }
        printMessage(response, message);
    }

    protected void returnWarn(HttpServletResponse response, String message) throws IOException {
        log.warn(message);
        printMessage(response, message);
    }

    protected void printMessage(HttpServletResponse response, String message) throws IOException {
        response.getOutputStream().print(message);
    }

}
