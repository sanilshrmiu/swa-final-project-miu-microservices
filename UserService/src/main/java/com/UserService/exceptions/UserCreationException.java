package com.UserService.exceptions;

public class UserCreationException extends RuntimeException {
    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException() {
        super("User creation failed.");
    }
}