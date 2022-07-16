package com.nisum.infraestructure.drivenadapters.database.user;

import com.nisum.domain.model.user.User;
import com.nisum.domain.model.user.UserResponse;

public class UserMapper {

    private UserMapper() {
    }

    public static UserEntity toEntity(User user) {
        var userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public static UserResponse toDto(UserEntity userEntity) {
        return UserResponse.from(userEntity.getUserId(), userEntity.getName(),
                null, null, null);
    }

    public static User toUserDto(UserEntity userEntity) {
        return User.builder().userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
