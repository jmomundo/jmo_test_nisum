package com.nisum.infraestructure.drivenadapters.database.user;

import com.nisum.domain.model.common.enums.InfrastructureExceptionEnum;
import com.nisum.infraestructure.entrypoints.apirest.exeption.exceptions.InfrastructureException;
import com.nisum.domain.model.user.User;
import com.nisum.domain.model.user.UserResponse;
import com.nisum.domain.model.user.gateways.UserRepository;
import com.nisum.infraestructure.drivenadapters.database.phone.PhoneEntityRepository;
import com.nisum.infraestructure.drivenadapters.database.phone.PhoneMapper;
import com.nisum.infraestructure.drivenadapters.database.session.SessionEntity;
import com.nisum.infraestructure.drivenadapters.database.session.SessionEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class UserAdapter implements UserRepository {

    private final UserEntityRepository userEntityRepository;
    private final PhoneEntityRepository phoneEntityRepository;

    private final SessionEntityRepository sessionEntityRepository;



    public UserAdapter(UserEntityRepository userEntityRepository, PhoneEntityRepository phoneEntityRepository, SessionEntityRepository sessionEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.phoneEntityRepository = phoneEntityRepository;
        this.sessionEntityRepository = sessionEntityRepository;
    }



    @Transactional
    public UserResponse save(User user, String token) {
        var userEntity = UserMapper.toEntity(user);
        var userCreate = userEntityRepository.save(userEntity);
        var phones = user.getPhones().stream().map(PhoneMapper::toEntity)
                .peek(phoneEntity -> phoneEntity.setUser(userCreate)).collect(Collectors.toSet());
        phoneEntityRepository.saveAll(phones);
        var sessionEntity = new SessionEntity();
        sessionEntity.setActive(Boolean.TRUE);
        sessionEntity.setToken(token);
        sessionEntity.setUser(userCreate);
        sessionEntity.setLastLogin(userCreate.getModified());
        var sessionCreate = sessionEntityRepository.save(sessionEntity);
        var response = UserMapper.toDto(userCreate);
        response.setCreated(userCreate.getCreated());
        response.setModified(userCreate.getModified());
        response.setToken(sessionCreate.getToken());
        response.setActive(sessionCreate.isActive());
        response.setLastLogin(sessionCreate.getLastLogin());
        return response;
    }

    @Transactional(readOnly = true)
    public Optional<User> login(String email, String password) {
        return Optional.of(UserMapper.toUserDto(userEntityRepository
                .findByEmailAndPassword(email, password).orElseThrow(() -> new InfrastructureException(
                        new Exception("User not found"),
                        InfrastructureExceptionEnum.USER_NOT_FOUND))));
    }
}
