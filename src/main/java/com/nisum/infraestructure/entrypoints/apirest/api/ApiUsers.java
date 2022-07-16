package com.nisum.infraestructure.entrypoints.apirest.api;

import com.nisum.domain.model.session.Login;
import com.nisum.domain.model.user.User;
import com.nisum.domain.usercase.session.SessionOperations;
import com.nisum.domain.usercase.user.UserOperations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@Validated
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiUsers {

    private final UserOperations userOperations;
    private final SessionOperations sessionOperations;

    public ApiUsers(UserOperations userOperations, SessionOperations sessionOperations) {
        this.userOperations = userOperations;
        this.sessionOperations = sessionOperations;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login login) {
        return ResponseEntity.ok(Map.of("token", sessionOperations.login(login)));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri())
                .body(userOperations.save(user));
    }
}
