package com.alejo.svautenticacion.applications.app.service.config;


import com.alejo.svautenticacion.domain.model.domain.token.gateway.TokeGateway;
import com.alejo.svautenticacion.domain.model.domain.user.gateway.UserRepository;
import com.alejo.svautenticacion.domain.usecase.UpdateProfileUseCase;
import com.alejo.svautenticacion.domain.usecase.ViewProfileUseCase;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class UseCaseConfig {

    @Bean
    public ObjectMapperImp createObjectMapper() {
        return new ObjectMapperImp();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UpdateProfileUseCase updateProfileUseCase(UserRepository userRepository
            , PasswordEncoder passwordEncoder
            , TokeGateway tokeGateway){

        return new UpdateProfileUseCase(userRepository,passwordEncoder,tokeGateway);

    }

    @Bean
    public ViewProfileUseCase viewProfileUseCase(UserRepository userRepository,TokeGateway tokeGateway){
        return new ViewProfileUseCase(userRepository,tokeGateway);
    }



}
