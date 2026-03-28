package com.nttdata.authservice.infrastructure.decorator;

import com.nttdata.authservice.api.dto.LoginRequestDto;
import com.nttdata.authservice.api.dto.LoginResponseDto;
import com.nttdata.authservice.api.dto.LogoutRequestDto;
import com.nttdata.authservice.api.dto.TokenRefreshRequestDto;
import com.nttdata.authservice.api.dto.TokenRefreshResponseDto;
import com.nttdata.authservice.api.dto.UserProfileResponseDto;
import com.nttdata.authservice.domain.port.AuthUseCase;
import com.nttdata.authservice.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class LoggingAuthUseCaseDecorator implements AuthUseCase {

    private final AuthService delegate;

    @Override
    public Mono<LoginResponseDto> login(LoginRequestDto request) {
        log.info("Login requested for user={}", request.getUsername());
        return delegate.login(request);
    }

    @Override
    public Mono<TokenRefreshResponseDto> refreshToken(TokenRefreshRequestDto request) {
        log.info("Token refresh requested");
        return delegate.refreshToken(request);
    }

    @Override
    public Mono<Void> logout(LogoutRequestDto request) {
        log.info("Logout requested");
        return delegate.logout(request);
    }

    @Override
    public Mono<UserProfileResponseDto> me(String username) {
        log.info("Profile requested for user={}", username);
        return delegate.me(username);
    }
}
