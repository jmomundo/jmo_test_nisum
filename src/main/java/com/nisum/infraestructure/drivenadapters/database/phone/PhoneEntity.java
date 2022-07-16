package com.nisum.infraestructure.drivenadapters.database.phone;


import com.nisum.infraestructure.drivenadapters.database.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "phones", schema = "public")
public class PhoneEntity {

    @Id
    @Column(name = "phone_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID phoneId;
    @Column(name = "number", nullable = false)
    private Integer number;
    @Column(name = "citycode", nullable = false)
    private Integer cityCode;
    @Column(name = "contrycode", nullable = false)
    private Integer contryCode;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
