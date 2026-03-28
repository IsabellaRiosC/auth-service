package com.nttdata.authservice.infrastructure.mongodb;

import java.time.Instant;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class MongoUserDataInitializer {

    @Bean
    ApplicationRunner authUserInitializer(UserMongoRepository userMongoRepository) {
        return args -> userMongoRepository.findByUsername("demo.user")
                .switchIfEmpty(Mono.defer(() -> {
                    UserDocument user = new UserDocument();
                    user.setId("USR_DEMO");
                    user.setUsername("demo.user");
                    user.setPasswordHash("demo123");
                    user.setEmail("demo.user@bank.local");
                    user.setRoleIds(List.of("ROLE_CUSTOMER"));
                    user.setStatus("ACTIVE");
                    user.setCreatedAt(Instant.now());
                    user.setUpdatedAt(Instant.now());
                    return userMongoRepository.save(user);
                }))
                .then()
                .subscribe();
    }
}
