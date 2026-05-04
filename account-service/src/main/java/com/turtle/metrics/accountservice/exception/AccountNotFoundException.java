package com.turtle.metrics.accountservice.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super("Account Not Found: " + message);
    }
}
