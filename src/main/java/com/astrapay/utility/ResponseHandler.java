package com.astrapay.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static <T> ResponseEntity<Object> generateResponse(
            String message,
            HttpStatus status,
            T data) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", status.is2xxSuccessful());
        map.put("message", message);
        map.put("data", data);
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateError(
            String message, HttpStatus status) {

        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("message", message);
        map.put("data", null);
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }
}
