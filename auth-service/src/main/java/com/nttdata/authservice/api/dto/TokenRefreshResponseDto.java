package com.nttdata.authservice.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRefreshResponseDto {

    private String accessToken;
    private String tokenType;
    private long expiresIn;
}
