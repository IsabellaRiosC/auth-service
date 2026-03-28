package com.nttdata.authservice.infrastructure.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserMongoRepository extends ReactiveMongoRepository<UserDocument, String> {

    Mono<UserDocument> findByUsername(String username);
}
