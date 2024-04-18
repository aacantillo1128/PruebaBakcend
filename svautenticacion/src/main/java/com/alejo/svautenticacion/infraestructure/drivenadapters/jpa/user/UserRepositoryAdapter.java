package com.alejo.svautenticacion.infraestructure.drivenadapters.jpa.user;


import com.alejo.svautenticacion.domain.model.domain.user.User;
import com.alejo.svautenticacion.domain.model.domain.user.gateway.UserRepository;
import com.alejo.svautenticacion.infraestructure.drivenadapters.jpa.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter extends AdapterOperations<User,UserData, Integer,UserDataRepository>
implements UserRepository {

    public UserRepositoryAdapter(UserDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, User.UserBuilder.class).build());
    }


    @Override
    public Optional<User> findByUsername(String username) {

        return Optional.ofNullable(super.toEntity(repository.findByUsername(username).get()));

    }

}