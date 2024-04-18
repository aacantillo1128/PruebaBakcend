package com.alejo.svautenticacion.domain.model.domain.exceptios;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
