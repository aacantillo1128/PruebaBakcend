package com.alejo.svautenticacion.domain.usecase;

import com.alejo.svautenticacion.domain.model.domain.exceptios.ForbiddenException;
import com.alejo.svautenticacion.domain.model.domain.exceptios.UserDoesNotExistException;
import com.alejo.svautenticacion.domain.model.domain.token.gateway.TokeGateway;
import com.alejo.svautenticacion.domain.model.domain.user.User;
import com.alejo.svautenticacion.domain.model.domain.user.gateway.UserRepository;
import com.alejo.svautenticacion.domain.model.domain.util.Utilities;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ViewProfileUseCase {

    private final UserRepository userRepository;
    private final TokeGateway tokeGateway;

    public User getProfile(String username, String token){

        token = token.substring(Utilities.INITIAL_POSITION_OF_THE_TOKEN);

        String usernameToken = tokeGateway.getUsernameFromToken(token);

        validateUsernameWithTokenUsername(usernameToken, username);

        return userRepository.findByUsername(username)
                .orElseThrow( () -> new UserDoesNotExistException(Utilities.USER_DOES_NOT_EXIST));
    }

    private void validateUsernameWithTokenUsername(String usernameToken, String username) {
        if(!username.equals(usernameToken)){
            throw new ForbiddenException(Utilities.OPERATION_NOT_ALLOWED);
        }
    }

}
