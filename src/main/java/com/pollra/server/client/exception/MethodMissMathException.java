package com.pollra.server.client.exception;

public class MethodMissMathException extends RuntimeException {
    public MethodMissMathException(String message) {
        super(message);
    }

    public MethodMissMathException(String message, Throwable cause) {
        super(message, cause);
    }
}
