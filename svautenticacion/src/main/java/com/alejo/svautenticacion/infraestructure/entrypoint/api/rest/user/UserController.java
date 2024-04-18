package com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user;


import com.alejo.svautenticacion.domain.model.domain.user.User;
import com.alejo.svautenticacion.domain.usecase.UpdateProfileUseCase;
import com.alejo.svautenticacion.domain.usecase.ViewProfileUseCase;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user.converter.UserConverter;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user.dto.UserRequestDTO;
import com.alejo.svautenticacion.infraestructure.entrypoint.api.rest.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UpdateProfileUseCase updateProfileUseCase;
    private final ViewProfileUseCase viewProfileUseCase;
    private final UserConverter converter;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getProfile(@RequestHeader("Authorization") String token
            , @PathVariable("username") String username){

        User user = viewProfileUseCase.getProfile(username,token);

        return ResponseEntity.ok(converter.toUserDTO(user));
    }

    @PostMapping
    public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token, @RequestBody UserRequestDTO userDTO){
        User user = converter.toUser(userDTO);
        User userUpdate = updateProfileUseCase.updateProfile(user,token);

        return ResponseEntity.ok(converter.toUserDTO(userUpdate));
    }

}
