package com.nisum.infraestructure.drivenadapters.database.phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.UUID;

public interface PhoneEntityRepository extends JpaRepository<PhoneEntity, UUID>, QueryByExampleExecutor<PhoneEntity> {
}
