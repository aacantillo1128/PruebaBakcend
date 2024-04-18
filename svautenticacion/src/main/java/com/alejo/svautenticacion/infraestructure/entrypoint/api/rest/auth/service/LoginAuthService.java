package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.service;

import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.dto.LoginRequestDTO;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.dto.TokenResponseDTO;
import com.alejo.svautenticacion.infraestructure.drivenadapters.jpa.user.UserDataRepository;
import com.alejo.svautenticacion.infraestructure.drivenadapters.security.jwt.service.JwtService;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.exceptions.UserNotFoundException;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.utils.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginAuthService {

    private final UserDataRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println("Paso por aqui");
        UserDetails user=userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(Utilities.USER_NOT_FOUND));
        String token=jwtService.getToken(user);

        return TokenResponseDTO.builder()
                .token(token)
                .build();

    }
}
