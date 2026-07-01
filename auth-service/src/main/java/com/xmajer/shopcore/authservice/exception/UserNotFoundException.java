package com.xmajer.shopcore.authservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UserNotFoundType type, Object value) {
        super("User with " + type.label() + " '" + value + "' was not found");
    }
}
