package com.surmin.phonebook.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException (String message) {
        super(message);
    }
}
