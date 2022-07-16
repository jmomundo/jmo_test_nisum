package com.nisum.infraestructure.drivenadapters.database.session;

import com.nisum.infraestructure.drivenadapters.database.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "sessions", schema = "public")
public class SessionEntity {

    @Id
    @Column(name = "session_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sessionId;
    @Column(name = "token", nullable = false)
    private String token;
    @Column(name = "isactive", nullable = false)
    private boolean isActive;
    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

}
