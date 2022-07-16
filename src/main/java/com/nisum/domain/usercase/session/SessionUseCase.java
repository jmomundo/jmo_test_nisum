package com.nisum.domain.usercase.session;

import com.nisum.domain.usercase.token.TokenGenerator;
import com.nisum.domain.model.session.Login;
import com.nisum.domain.model.session.Session;
import com.nisum.domain.model.session.gateways.SessionRepository;
import com.nisum.domain.model.user.gateways.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class SessionUseCase implements SessionOperations{

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final SessionRepository sessionRepository;

    public SessionUseCase(UserRepository userRepository,
                          TokenGenerator tokenGenerator, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.sessionRepository = sessionRepository;
    }

    public String login(Login login) {

        var user = userRepository.login(login.getEmail(), login.getPassword());
        if (user.isPresent()) {
            var token = tokenGenerator.generate(login);
            var session = Session.builder()
                    .user(user.get())
                    .token(token)
                    .isActive(Boolean.TRUE)
                    .build();
            sessionRepository.save(session);
            return session.getToken();
        }
        return null;

    }
}
