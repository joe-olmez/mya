package com.olmez.mya.springsecurity.securityutiliy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtility {

    private static final String MSG_SUCCESS = "Successful";

    /**
     * This method is used so that the data returned from the RestControls can be
     * detected as JSON by Angular. It allows Angular to parse the Response object
     * as JSON.
     * 
     * @param responseObj
     * @return JSON of the responseObj
     */
    public static ResponseEntity<Object> genSuccessfulResponse(Object responseObj) {
        return generateResponse(MSG_SUCCESS, HttpStatus.OK, responseObj);
    }

    public static ResponseEntity<Object> genSuccessfulResponse(Object responseObj, Object user) {
        return generateResponse(MSG_SUCCESS, HttpStatus.OK, responseObj, user);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        return generateResponse(message, status, responseObj, null);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj,
            Object user) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("user", user);
        return new ResponseEntity<Object>(map, status);
    }

}
