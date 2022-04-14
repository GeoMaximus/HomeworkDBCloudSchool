package com.db.config.exceptions;

public class ExistingAccountException extends Exception {
    public ExistingAccountException(String message) {
        super(message);
    }
}
