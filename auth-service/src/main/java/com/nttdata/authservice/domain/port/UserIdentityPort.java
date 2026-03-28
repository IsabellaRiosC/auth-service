package com.nttdata.authservice.domain.port;

import java.util.List;
import reactor.core.publisher.Mono;

public interface UserIdentityPort {

    Mono<UserIdentity> findByUsername(String username);

    record UserIdentity(
            String userId,
            String username,
            String email,
            String passwordHash,
            List<String> roles,
            String status) {
    }
}
