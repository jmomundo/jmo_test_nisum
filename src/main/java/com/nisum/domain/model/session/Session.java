package com.nisum.domain.model.session;

import com.nisum.domain.model.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    private UUID sessionId;
    private String token;
    private boolean isActive;
    private LocalDateTime lastLogin;
    private User user;


}
