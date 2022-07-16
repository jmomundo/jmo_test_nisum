package com.nisum.domain.usercase.user;

import com.nisum.domain.usercase.token.TokenGenerator;
import com.nisum.domain.model.session.Login;
import com.nisum.domain.model.user.User;
import com.nisum.domain.model.user.gateways.UserRepository;
import com.nisum.domain.usercase.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase implements  UserOperations{


    private UserRepository userRepository;

    private TokenGenerator tokenGenerator;

    public UserUseCase(UserRepository userRepository,TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public User save(User user){
        Utils.validPatternEmail(user.getEmail());
        Utils.validPatternPassword(user.getPassword());
        var token = tokenGenerator.generate(Login.builder().email(user.getEmail()).build());
        return userRepository.save(user,token);
    }
}
