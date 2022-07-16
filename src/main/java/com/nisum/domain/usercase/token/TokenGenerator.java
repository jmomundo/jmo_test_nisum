package com.nisum.domain.usercase.token;


import com.nisum.domain.model.session.Login;

public interface TokenGenerator {

    String generate(Login login);
}
