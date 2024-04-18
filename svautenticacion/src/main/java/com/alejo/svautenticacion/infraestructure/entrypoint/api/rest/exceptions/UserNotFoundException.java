package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
       super(message);
    }
}
