package com.nisum.infrastructure.drivenadapters.user;

import com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions.InfrastructureException;
import com.nisum.domain.model.phone.Phone;
import com.nisum.domain.model.user.User;
import com.nisum.infraestructure.drivenadapters.database.phone.PhoneEntityRepository;
import com.nisum.infraestructure.drivenadapters.database.session.SessionEntity;
import com.nisum.infraestructure.drivenadapters.database.session.SessionEntityRepository;
import com.nisum.infraestructure.drivenadapters.database.user.UserAdapter;
import com.nisum.infraestructure.drivenadapters.database.user.UserEntity;
import com.nisum.infraestructure.drivenadapters.database.user.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
class UserAdapterTest {

    UserEntityRepository userEntityRepository;
    PhoneEntityRepository phoneEntityRepository;
    SessionEntityRepository sessionEntityRepository;

    UserAdapter userAdapter;

    UserEntity userEntityMock;
    SessionEntity sessionEntityMock;
    User userMock;
    Set<Phone> phonesMock;



    @BeforeEach
    void init() {

        userEntityMock = new UserEntity();
        userEntityMock.setUserId(UUID.randomUUID());
        userEntityMock.setEmail("any@email.com");
        userEntityMock.setName("any name test");
        userEntityMock.setModified(LocalDateTime.now());
        userEntityMock.setCreated(LocalDateTime.now());
        userEntityMock.setPassword("xxxxxxx");

        sessionEntityMock = new SessionEntity();
        sessionEntityMock.setLastLogin(LocalDateTime.now());
        sessionEntityMock.setUser(userEntityMock);
        sessionEntityMock.setSessionId(UUID.randomUUID());
        sessionEntityMock.setActive(true);
        sessionEntityMock.setToken("any.token.mock");

        phonesMock = Set.of(Phone.builder().cityCode(57).contryCode(123).number(3456789).build());
        userMock = User.builder()
                .password(userEntityMock.getPassword())
                .email(userEntityMock.getEmail())
                .name(userEntityMock.getName())
                .phones(phonesMock)
                .build();

        userEntityRepository = mock(UserEntityRepository.class);
        phoneEntityRepository = mock(PhoneEntityRepository.class);
        sessionEntityRepository = mock(SessionEntityRepository.class);
        userAdapter = new UserAdapter(userEntityRepository, phoneEntityRepository, sessionEntityRepository);
    }

    @Test
    void save() {
        var tokenMock = "any.token.mock";
        when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntityMock);
        when(phoneEntityRepository.saveAll(any())).thenReturn(List.of());
        when(sessionEntityRepository.save(any(SessionEntity.class))).thenReturn(sessionEntityMock);
        var response = userAdapter.save(userMock, tokenMock);
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo(tokenMock);
    }

    @TestFactory
    Stream<DynamicTest> login() {
        var emailMock = "any@email.com";
        var passwordMock = "xxxxxx";

        return Stream.of(
                dynamicTest("login success", () -> {
                    when(userEntityRepository.findByEmailAndPassword(anyString(), anyString()))
                            .thenReturn(Optional.of(userEntityMock));
                    var response = userAdapter.login(emailMock, passwordMock);
                    assertThat(response).isPresent();
                }),
                dynamicTest("login error", () -> {
                    when(userEntityRepository.findByEmailAndPassword(anyString(), anyString()))
                            .thenReturn(Optional.empty());
                    assertThrows(InfrastructureException.class,
                            () -> userAdapter.login(emailMock, passwordMock));
                })
        );
    }
}
