package com.alejo.svautenticacion.domain.usecase;

import com.alejo.svautenticacion.domain.model.domain.exceptios.ForbiddenException;
import com.alejo.svautenticacion.domain.model.domain.exceptios.UserDoesNotExistException;
import com.alejo.svautenticacion.domain.model.domain.token.gateway.TokeGateway;
import com.alejo.svautenticacion.domain.model.domain.user.User;
import com.alejo.svautenticacion.domain.model.domain.user.gateway.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ViewProfileUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokeGateway tokeGateway;


    private ViewProfileUseCase viewProfileUseCase;

    @BeforeEach
    void setUp() {
        viewProfileUseCase = new ViewProfileUseCase(userRepository,tokeGateway);
    }

    @Test
    public void getProfileShouldReturnTheProfileWhenTheUsernameExistsAndMatchesTheTokensUsername(){

        String token = "ansksdkfpf..";
        String usernameToken = "Alex17";
        String username = "Alex17";
        String email = "alex@gmail.com";
        String password = "12345";
        User user = User.builder()
                .id(1)
                .username(username)
                .email(email)
                .password(password)
                .role("USER").build();

        Optional<User> optionalUser = Optional.ofNullable(user);


        Mockito.when(tokeGateway.getUsernameFromToken(Mockito.anyString())).thenReturn(usernameToken);
        Mockito.when(userRepository.findByUsername(username)).thenReturn(optionalUser);



        User userReseult = viewProfileUseCase.getProfile(username,token);

        assertEquals(user.getId(),userReseult.getId());
        assertEquals(user.getEmail(),userReseult.getEmail());
        assertEquals(user.getPassword(),userReseult.getPassword());
        assertEquals(user.getRole(),userReseult.getRole());
        assertEquals(user.getUsername(),userReseult.getUsername());
        Mockito.verify(userRepository).findByUsername(username);
        Mockito.verify(tokeGateway).getUsernameFromToken(Mockito.anyString());


    }

    @Test
    public void getProfileShouldReturnForbiddenExceptionWhenTheUsernameNotMatchesTheTokensUsername(){

        String token = "ansksdkfpf..";
        String usernameToken = "Alex171";
        String username = "Alex17";
        String email = "alex@gmail.com";
        String password = "12345";
        User user = User.builder()
                .id(1)
                .username(username)
                .email(email)
                .password(password)
                .role("USER").build();

        Optional<User> optionalUser = Optional.ofNullable(user);


        ForbiddenException exception = assertThrows(ForbiddenException.class,
                ()->viewProfileUseCase.getProfile(username,token));


        Mockito.verify(tokeGateway).getUsernameFromToken(Mockito.anyString());

    }

    @Test
    public void getProfileShouldReturnUserDoesNotExistExceptionWhenTheUsernameNotExist(){

        String token = "ansksdkfpf..";
        String usernameToken = "Alex17";
        String username = "Alex17";
        String email = "alex@gmail.com";
        String password = "12345";
        User user = User.builder()
                .id(1)
                .username(username)
                .email(email)
                .password(password)
                .role("USER").build();

        Optional<User> optionalUser = Optional.ofNullable(user);

        Mockito.when(tokeGateway.getUsernameFromToken(Mockito.anyString())).thenReturn(usernameToken);

        UserDoesNotExistException exception = assertThrows(UserDoesNotExistException.class,
                ()->viewProfileUseCase.getProfile(username,token));

        Mockito.verify(userRepository).findByUsername(username);
        Mockito.verify(tokeGateway).getUsernameFromToken(Mockito.anyString());

    }


}