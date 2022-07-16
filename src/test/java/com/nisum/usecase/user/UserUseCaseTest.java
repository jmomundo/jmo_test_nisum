package com.nisum.usecase.user;

import com.nisum.domain.usercase.token.TokenGenerator;
import com.nisum.domain.usercase.user.UserOperations;
import com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions.DomainException;
import com.nisum.domain.model.phone.Phone;
import com.nisum.domain.model.session.Login;
import com.nisum.domain.model.user.User;
import com.nisum.domain.model.user.UserResponse;
import com.nisum.domain.model.user.gateways.UserRepository;
import com.nisum.domain.usercase.user.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    UserRepository userRepository;

    TokenGenerator tokenGenerator;

    UserOperations userOperations;

    @BeforeEach
    public void init(){
        userRepository = mock(UserRepository.class);
        tokenGenerator = mock(TokenGenerator.class);
        userOperations = new UserUseCase(userRepository,tokenGenerator);
    }

    @Test
    public void save(){
        var tokenMock = "any.token.mock";
        var userResponseMock = UserResponse.from(UUID.randomUUID(),"",
                "","", Set.of(new Phone()));

        var userMock = User.builder().email("prueba@prueba.com").password("PRUEBA123##asd").build();
        when(tokenGenerator.generate(any(Login.class))).thenReturn(tokenMock);
        when(userRepository.save(any(User.class),anyString())).thenReturn(userResponseMock);

        var responseTest = userOperations.save(userMock);

        assertThat(responseTest).isNotNull();
        assertThat(responseTest).isEqualTo(userResponseMock);
    }

    @Test
    public void saveErrorPassword(){
        var userMock = User.builder().email("prueba@prueba.com").password("malo").build();
        assertThrows(DomainException.class,
                () -> userOperations.save(userMock));
    }

}
