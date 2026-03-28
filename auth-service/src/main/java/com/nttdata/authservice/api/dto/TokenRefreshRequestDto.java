package com.nttdata.authservice.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRefreshRequestDto {

    @NotBlank
    private String refreshToken;
}
