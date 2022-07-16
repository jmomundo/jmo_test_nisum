package com.nisum.infraestructure.drivenadapters.database.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID>, QueryByExampleExecutor<UserEntity> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
