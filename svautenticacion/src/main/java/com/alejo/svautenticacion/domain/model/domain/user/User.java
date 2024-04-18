package com.alejo.svautenticacion.domain.model.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String role;

}
