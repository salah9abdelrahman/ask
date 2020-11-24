package com.salah.ask.exception.custom;

public class EntityBadRequestException extends RuntimeException {
    public EntityBadRequestException() {
    }

    public EntityBadRequestException(String message) {
        super(message);
    }

    public EntityBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
