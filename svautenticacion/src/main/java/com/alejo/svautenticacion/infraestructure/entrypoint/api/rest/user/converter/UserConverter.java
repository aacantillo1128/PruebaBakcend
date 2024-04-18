package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user.converter;

import com.alejo.svautenticacion.domain.model.domain.user.User;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user.dto.UserRequestDTO;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponseDTO toUserDTO(User user){
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .id(user.getId())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }

    public User toUser(UserRequestDTO userDTO){
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();
    }


}
