package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth;

import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.dto.LoginRequestDTO;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.dto.RegisterRequestDTO;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.dto.TokenResponseDTO;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.service.LoginAuthService;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.auth.service.RegisterAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final LoginAuthService loginAuthService;
    private final RegisterAuthService registerAuthService;

    @PostMapping(value = "login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO request)
    {

        return ResponseEntity.ok(loginAuthService.login(request));

    }

    @PostMapping(value = "register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO request)
    {

        return ResponseEntity.ok(registerAuthService.register(request));

    }
}