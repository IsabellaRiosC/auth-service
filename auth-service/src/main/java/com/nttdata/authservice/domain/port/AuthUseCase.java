package com.nttdata.authservice.domain.port;

import com.nttdata.authservice.api.dto.LoginRequestDto;
import com.nttdata.authservice.api.dto.LoginResponseDto;
import com.nttdata.authservice.api.dto.LogoutRequestDto;
import com.nttdata.authservice.api.dto.TokenRefreshRequestDto;
import com.nttdata.authservice.api.dto.TokenRefreshResponseDto;
import com.nttdata.authservice.api.dto.UserProfileResponseDto;
import reactor.core.publisher.Mono;

public interface AuthUseCase {

    Mono<LoginResponseDto> login(LoginRequestDto request);

    Mono<TokenRefreshResponseDto> refreshToken(TokenRefreshRequestDto request);

    Mono<Void> logout(LogoutRequestDto request);

    Mono<UserProfileResponseDto> me(String username);
}
