package com.UserService.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
// public class ErrorResponse {
//     String error;
//     int status;
// }
public class ErrorResponse {
    
    private Map<String, String> errors;

    public ErrorResponse() {
        this.errors = new HashMap<>();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public void addError(String field, String message) {
        errors.put(field, message);
    }
}