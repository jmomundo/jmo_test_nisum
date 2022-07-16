package com.nisum.infraestructure.drivenadapters.database.user;

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
@Table(name = "users", schema = "public")
public class UserEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @Column(name = "modified", nullable = false)
    private LocalDateTime modified;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
        modified = LocalDateTime.now();
    }
}
