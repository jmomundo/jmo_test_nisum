package com.nisum.infraestructure.drivenadapters.database.session;

import com.nisum.domain.model.session.Session;
import com.nisum.domain.model.session.gateways.SessionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class SessionAdapter implements SessionRepository {

    private final SessionEntityRepository sessionEntityRepository;

    public SessionAdapter(SessionEntityRepository sessionEntityRepository) {
        this.sessionEntityRepository = sessionEntityRepository;
    }

    public void save(Session session) {
        var sessionDb = sessionEntityRepository.findByUserUserId(session.getUser().getUserId()).orElseGet(() -> {
            var sessionEntity = SessionMapper.toEntity(session);
            sessionEntity.setLastLogin(LocalDateTime.now());
            return sessionEntityRepository.save(sessionEntity);
        });
        sessionDb.setLastLogin(LocalDateTime.now());
        sessionEntityRepository.save(sessionDb);
    }
}
