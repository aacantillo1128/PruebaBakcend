package com.alejo.svautenticacion.domain.model.domain.exceptios;

public class ForbiddenException extends  RuntimeException{

    public ForbiddenException(String message) {
        super(message);
    }
}
