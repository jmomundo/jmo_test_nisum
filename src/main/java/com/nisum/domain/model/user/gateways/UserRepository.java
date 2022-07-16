package com.nisum.domain.model.user.gateways;


import com.nisum.domain.model.user.User;
import com.nisum.domain.model.user.UserResponse;

import java.util.Optional;

public interface UserRepository {

    User save(User user, String token);
    Optional<User> login(String email, String password);
}
