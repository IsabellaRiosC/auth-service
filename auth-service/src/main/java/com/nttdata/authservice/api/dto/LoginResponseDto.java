package com.nttdata.authservice.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;
}
