package com.xmajer.shopcore.authservice.exception;

public enum UserNotFoundType {
    EMAIL("email"),
    ID("id");

    private final String label;

    UserNotFoundType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}