package com.nisum.domain.model.session.gateways;

import com.nisum.domain.model.session.Session;

public interface SessionRepository {

    void save(Session session);
}
