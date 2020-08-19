package com.pollra.server.client.exception;

public class TypeMissMathException extends RuntimeException {
    public TypeMissMathException(String message) {
        super(message);
    }

    public TypeMissMathException(String message, Throwable cause) {
        super(message, cause);
    }
}
