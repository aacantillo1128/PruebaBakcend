package com.alejo.svautenticacion.domain.usecase;


import com.alejo.svautenticacion.domain.model.domain.exceptios.ForbiddenException;
import com.alejo.svautenticacion.domain.model.domain.exceptios.UserDoesNotExistException;
import com.alejo.svautenticacion.domain.model.domain.token.gateway.TokeGateway;
import com.alejo.svautenticacion.domain.model.domain.user.User;
import com.alejo.svautenticacion.domain.model.domain.user.gateway.UserRepository;
import com.alejo.svautenticacion.domain.model.domain.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class UpdateProfileUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokeGateway tokeGateway;

    public User updateProfile(User user, String token){

        String username = tokeGateway.getUsernameFromToken(token.substring(Utilities.INITIAL_POSITION_OF_THE_TOKEN));

        validateUsernameWithTokenUsername(user, username);

        User userToUpdate = userRepository
                .findByUsername(user.getUsername())
                .orElseThrow(() -> new UserDoesNotExistException(Utilities.USER_NOT_UPDATED));


        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userToUpdate);
    }

    private void validateUsernameWithTokenUsername(User user, String username) {
        if(!username.equals(user.getUsername())){
            throw new ForbiddenException(Utilities.OPERATION_NOT_ALLOWED);
        }
    }

}
