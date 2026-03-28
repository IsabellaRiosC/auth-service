package com.nttdata.authservice.infrastructure.mongodb;

import com.nttdata.authservice.domain.port.UserIdentityPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MongoUserIdentityAdapter implements UserIdentityPort {

    private final UserMongoRepository userMongoRepository;

    public MongoUserIdentityAdapter(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public Mono<UserIdentity> findByUsername(String username) {
        return userMongoRepository.findByUsername(username)
                .map(document -> new UserIdentity(
                        document.getId(),
                        document.getUsername(),
                        document.getEmail(),
                        document.getPasswordHash(),
                        document.getRoleIds(),
                        document.getStatus()));
    }
}
