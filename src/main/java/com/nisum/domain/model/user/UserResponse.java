package com.nisum.domain.model.user;

import com.nisum.domain.model.phone.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends User {
    private LocalDateTime created;
    private LocalDateTime modified;
    private String token;
    private boolean isActive;
    private LocalDateTime lastLogin;

    private UserResponse(UUID userId, String name, String email, String password, Set<Phone> phones) {
        super(userId, name, email, password, phones);
    }

    public static UserResponse from(UUID userId, String name, String email, String password, Set<Phone> phones) {
        return new UserResponse(userId, name, email, password, phones);
    }

}
