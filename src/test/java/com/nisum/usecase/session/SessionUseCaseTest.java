package com.nisum.usecase.session;

import com.nisum.domain.usercase.token.TokenGenerator;
import com.nisum.domain.model.session.Login;
import com.nisum.domain.model.session.Session;
import com.nisum.domain.model.session.gateways.SessionRepository;
import com.nisum.domain.model.user.User;
import com.nisum.domain.model.user.gateways.UserRepository;
import com.nisum.domain.usercase.session.SessionOperations;
import com.nisum.domain.usercase.session.SessionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionUseCaseTest {

    UserRepository userRepository;

    SessionRepository sessionRepository;

    TokenGenerator tokenGenerator;
    SessionOperations sessionOperations;



    @BeforeEach
    public void init(){
        userRepository = mock(UserRepository.class);
        sessionRepository = mock(SessionRepository.class);
        tokenGenerator = mock(TokenGenerator.class);
        sessionOperations = new SessionUseCase(userRepository, tokenGenerator,sessionRepository);
    }

    @Test
    public void login(){


        var loginMock = Login.builder().email("test@test.com").password("test321").build();
        var tokenMock = "any.token.mock";
        var userMock = User.builder().userId(UUID.randomUUID()).build();
        var sessionMock = Session.builder().isActive(Boolean.TRUE).token(tokenMock).user(userMock).build();

        when(userRepository.login(anyString(), anyString())).thenReturn(Optional.of(userMock));

        when(tokenGenerator.generate(any(Login.class))).thenReturn(tokenMock);

        doNothing().when(sessionRepository).save(sessionMock);

        var response = sessionOperations.login(loginMock);

        assertThat(response).isNotNull();
    }

    @Test
    public void loginUSerNotFound(){

        var loginMock = Login.builder().email("usernotfound@test.com").password("test322").build();
        when(userRepository.login(anyString(), anyString())).thenReturn(Optional.empty());

        var response = sessionOperations.login(loginMock);

        assertThat(response).isNull();
    }


}
