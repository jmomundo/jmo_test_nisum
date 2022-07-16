package com.nisum.infraestructure.drivenadapters.database.session;

import com.nisum.infraestructure.drivenadapters.database.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;
import java.util.UUID;

public interface SessionEntityRepository extends JpaRepository<SessionEntity, UUID>, QueryByExampleExecutor<SessionEntity> {
    Optional<SessionEntity> findByUserUserId(UUID userId);
}
