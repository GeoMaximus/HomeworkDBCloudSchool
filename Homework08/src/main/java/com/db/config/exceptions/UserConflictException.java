package com.db.config.exceptions;

public class UserConflictException extends Exception {
    public UserConflictException(String message) {
        super(message);
    }
}
