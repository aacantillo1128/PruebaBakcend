package com.alejo.svautenticacion.domain.model.domain.user.gateway;

import com.alejo.svautenticacion.domain.model.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findByUsername(String username);


}
