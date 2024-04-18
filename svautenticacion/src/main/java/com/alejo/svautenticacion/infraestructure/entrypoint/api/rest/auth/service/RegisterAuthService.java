package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.service;

import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.dto.RegisterRequestDTO;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.dto.TokenResponseDTO;
import com.alejo.svautenticacion.infraestructure.drivenadapters.jpa.user.RoleData;
import com.alejo.svautenticacion.infraestructure.drivenadapters.jpa.user.UserData;
import com.alejo.svautenticacion.infraestructure.drivenadapters.jpa.user.UserDataRepository;
import com.alejo.svautenticacion.infraestructure.drivenadapters.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAuthService {

    private final UserDataRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO register(RegisterRequestDTO request) {
        UserData user = UserData.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(RoleData.USER)
                .build();

        userRepository.save(user);

        return TokenResponseDTO.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}
