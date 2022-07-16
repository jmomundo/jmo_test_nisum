package com.nisum.infrastructure.drivenadapters.session;

import com.nisum.domain.model.session.Session;
import com.nisum.domain.model.user.User;
import com.nisum.infraestructure.drivenadapters.database.session.SessionAdapter;
import com.nisum.infraestructure.drivenadapters.database.session.SessionEntity;
import com.nisum.infraestructure.drivenadapters.database.session.SessionEntityRepository;
import com.nisum.infraestructure.drivenadapters.database.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
class SessionAdapterTest {

    @MockBean
    SessionEntityRepository sessionEntityRepository;

    UserEntity userEntityMock;
    SessionEntity sessionEntityMock;

    SessionAdapter sessionAdapter;

    @BeforeEach
    void init() {
        openMocks(this);

        userEntityMock = new UserEntity();
        userEntityMock.setUserId(UUID.randomUUID());
        userEntityMock.setEmail("any@email.com");
        userEntityMock.setName("any name test");
        userEntityMock.setModified(LocalDateTime.now());
        userEntityMock.setCreated(LocalDateTime.now());
        userEntityMock.setPassword("xxxxxxxx");

        sessionEntityMock = new SessionEntity();
        sessionEntityMock.setLastLogin(LocalDateTime.now());
        sessionEntityMock.setUser(userEntityMock);
        sessionEntityMock.setSessionId(UUID.randomUUID());
        sessionEntityMock.setActive(true);
        sessionEntityMock.setToken("any.token.mock");

        sessionAdapter = new SessionAdapter(sessionEntityRepository);
    }

    @TestFactory
    Stream<DynamicTest> login() {
        var session = Session.builder()
                .lastLogin(LocalDateTime.now())
                .token("any.token.mock")
                .user(User.builder().userId(UUID.randomUUID()).build())
                .build();

        return Stream.of(
                dynamicTest("returns a record", () -> {
                    when(sessionEntityRepository.findByUserUserId(any(UUID.class)))
                            .thenReturn(Optional.of(sessionEntityMock));
                    sessionAdapter.save(session);
                    verify(sessionEntityRepository, times(1))
                            .save(sessionEntityMock);
                }),
                dynamicTest("throws exception", () -> {
                    when(sessionEntityRepository.findByUserUserId(any(UUID.class)))
                            .thenReturn(Optional.empty());
                    when(sessionEntityRepository.save(any(SessionEntity.class)))
                            .thenReturn(sessionEntityMock);
                    sessionAdapter.save(session);
                    verify(sessionEntityRepository, times(2))
                            .findByUserUserId(session.getUser().getUserId());
                })
        );
    }
}
