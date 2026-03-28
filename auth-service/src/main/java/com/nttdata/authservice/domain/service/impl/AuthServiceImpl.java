package com.nttdata.authservice.domain.service.impl;

import com.nttdata.authservice.api.dto.LoginRequestDto;
import com.nttdata.authservice.api.dto.LoginResponseDto;
import com.nttdata.authservice.api.dto.LogoutRequestDto;
import com.nttdata.authservice.api.dto.TokenRefreshRequestDto;
import com.nttdata.authservice.api.dto.TokenRefreshResponseDto;
import com.nttdata.authservice.api.dto.UserProfileResponseDto;
import com.nttdata.authservice.domain.mapper.AuthMapper;
import com.nttdata.authservice.domain.policy.AuthValidationPolicy;
import com.nttdata.authservice.domain.policy.TokenPolicy;
import com.nttdata.authservice.domain.port.UserIdentityPort;
import com.nttdata.authservice.domain.service.AuthService;
import com.nttdata.authservice.generated.api.AuthApiDelegate;
import com.nttdata.authservice.generated.model.LoginRequest;
import com.nttdata.authservice.generated.model.LoginResponse;
import com.nttdata.authservice.generated.model.LogoutRequest;
import com.nttdata.authservice.generated.model.TokenRefreshRequest;
import com.nttdata.authservice.generated.model.TokenRefreshResponse;
import com.nttdata.authservice.generated.model.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, AuthApiDelegate {

    private final AuthValidationPolicy authValidationPolicy;
    private final TokenPolicy tokenPolicy;
    private final UserIdentityPort userIdentityPort;

    @Override
    public Mono<LoginResponseDto> login(LoginRequestDto request) {
        authValidationPolicy.validateCredentials(request.getUsername(), request.getPassword());
        return userIdentityPort.findByUsername(request.getUsername())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials")))
                .map(userIdentity -> {
                    authValidationPolicy.validatePassword(request.getPassword(), userIdentity.passwordHash());
                    return LoginResponseDto.builder()
                            .accessToken(tokenPolicy.generateAccessToken(userIdentity.username()))
                            .refreshToken(tokenPolicy.generateRefreshToken())
                            .tokenType("Bearer")
                            .expiresIn(1800)
                            .build();
                });
    }

    @Override
    public Mono<TokenRefreshResponseDto> refreshToken(TokenRefreshRequestDto request) {
        return Mono.fromSupplier(() -> {
            authValidationPolicy.validateRefreshToken(request.getRefreshToken());
            return TokenRefreshResponseDto.builder()
                    .accessToken(tokenPolicy.generateAccessToken("demo.user"))
                    .tokenType("Bearer")
                    .expiresIn(1800)
                    .build();
        });
    }

    @Override
    public Mono<Void> logout(LogoutRequestDto request) {
        return Mono.fromRunnable(() -> authValidationPolicy.validateRefreshToken(request.getRefreshToken()));
    }

    @Override
    public Mono<UserProfileResponseDto> me(String username) {
        return userIdentityPort.findByUsername(username)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")))
                .map(AuthMapper::toUserProfile);
    }

    @Override
    public Mono<ResponseEntity<LoginResponse>> login(Mono<LoginRequest> loginRequest, ServerWebExchange exchange) {
        return loginRequest
                .map(this::toLoginRequestDto)
                .flatMap(this::login)
                .map(this::toLoginResponse)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<TokenRefreshResponse>> refreshToken(
            Mono<TokenRefreshRequest> tokenRefreshRequest,
            ServerWebExchange exchange) {
        return tokenRefreshRequest
                .map(this::toTokenRefreshRequestDto)
                .flatMap(this::refreshToken)
                .map(this::toTokenRefreshResponse)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> logout(Mono<LogoutRequest> logoutRequest, ServerWebExchange exchange) {
        return logoutRequest
                .map(this::toLogoutRequestDto)
                .flatMap(this::logout)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Override
    public Mono<ResponseEntity<UserProfileResponse>> me(ServerWebExchange exchange) {
        String username = exchange.getRequest().getHeaders().getFirst("X-Auth-User");
        if (username == null || username.isBlank()) {
            username = "demo.user";
        }

        return me(username)
                .map(this::toUserProfileResponse)
                .map(ResponseEntity::ok);
    }

    private LoginRequestDto toLoginRequestDto(LoginRequest request) {
        LoginRequestDto dto = new LoginRequestDto();
        dto.setUsername(request.getUsername());
        dto.setPassword(request.getPassword());
        return dto;
    }

    private TokenRefreshRequestDto toTokenRefreshRequestDto(TokenRefreshRequest request) {
        TokenRefreshRequestDto dto = new TokenRefreshRequestDto();
        dto.setRefreshToken(request.getRefreshToken());
        return dto;
    }

    private LogoutRequestDto toLogoutRequestDto(LogoutRequest request) {
        LogoutRequestDto dto = new LogoutRequestDto();
        dto.setRefreshToken(request.getRefreshToken());
        return dto;
    }

    private LoginResponse toLoginResponse(LoginResponseDto response) {
        return new LoginResponse()
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .tokenType(response.getTokenType())
                .expiresIn(response.getExpiresIn());
    }

    private TokenRefreshResponse toTokenRefreshResponse(TokenRefreshResponseDto response) {
        return new TokenRefreshResponse()
                .accessToken(response.getAccessToken())
                .tokenType(response.getTokenType())
                .expiresIn(response.getExpiresIn());
    }

    private UserProfileResponse toUserProfileResponse(UserProfileResponseDto response) {
        return new UserProfileResponse()
                .userId(response.getUserId())
                .username(response.getUsername())
                .email(response.getEmail())
                .roles(response.getRoles());
    }
}
