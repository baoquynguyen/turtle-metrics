package com.turtle.metrics.accountservice.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String message) {
        super("Account Already Exists: " + message);
    }
}
