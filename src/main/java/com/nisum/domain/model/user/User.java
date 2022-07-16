package com.nisum.domain.model.user;

import com.nisum.domain.model.phone.Phone;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private UUID userId;
    private String name;
    private String email;
    private String password;
    private Set<Phone> phones;

}
