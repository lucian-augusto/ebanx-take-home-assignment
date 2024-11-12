package com.lucianaugusto.ebanxassignment.base.error;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
