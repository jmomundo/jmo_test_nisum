package com.nisum.infraestructure.drivenadapters.database.session;


import com.nisum.domain.model.session.Session;
import com.nisum.infraestructure.drivenadapters.database.user.UserMapper;

public class SessionMapper {

    private SessionMapper() {}

    public static SessionEntity toEntity(Session session) {
        var sessionEntity = new SessionEntity();
        sessionEntity.setSessionId(session.getSessionId());
        sessionEntity.setToken(session.getToken());
        sessionEntity.setLastLogin(session.getLastLogin());
        sessionEntity.setActive(session.isActive());
        sessionEntity.setUser(UserMapper.toEntity(session.getUser()));
        return sessionEntity;
    }
}
