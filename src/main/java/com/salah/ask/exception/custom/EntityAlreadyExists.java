package com.salah.ask.exception.custom;

public class EntityAlreadyExists extends RuntimeException{
    public EntityAlreadyExists() {
    }

    public EntityAlreadyExists(String message) {
        super(message);
    }

    public EntityAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
