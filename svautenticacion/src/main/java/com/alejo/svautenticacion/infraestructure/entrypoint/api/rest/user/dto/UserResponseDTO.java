package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class UserResponseDTO {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
}
