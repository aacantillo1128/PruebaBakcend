package com.alejo.svautenticacion.infraestructure.drivenadapters.jpa.user;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface UserDataRepository extends CrudRepository<UserData, Integer>, QueryByExampleExecutor<UserData> {

    Optional<UserData> findByUsername(String username);

}
