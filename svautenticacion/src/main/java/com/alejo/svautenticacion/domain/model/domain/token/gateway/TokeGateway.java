package com.alejo.svautenticacion.domain.model.domain.token.gateway;


public interface TokeGateway {
    String getUsernameFromToken(String token);

}
