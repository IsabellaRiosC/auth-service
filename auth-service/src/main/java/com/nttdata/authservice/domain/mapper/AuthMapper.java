package com.nttdata.authservice.domain.mapper;

import com.nttdata.authservice.api.dto.UserProfileResponseDto;
import com.nttdata.authservice.domain.port.UserIdentityPort.UserIdentity;

public final class AuthMapper {

    private AuthMapper() {
    }

    public static UserProfileResponseDto toUserProfile(UserIdentity userIdentity) {
        return UserProfileResponseDto.builder()
                .userId(userIdentity.userId())
                .username(userIdentity.username())
                .email(userIdentity.email())
                .roles(userIdentity.roles())
                .build();
    }
}
