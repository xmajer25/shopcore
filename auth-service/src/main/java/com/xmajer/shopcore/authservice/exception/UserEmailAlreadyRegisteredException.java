package com.xmajer.shopcore.authservice.exception;

public class UserEmailAlreadyRegisteredException extends RuntimeException {
    public UserEmailAlreadyRegisteredException(String email){
        super("User with email: '" + email + "' already exists");
    }
}
