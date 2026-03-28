package com.nttdata.authservice.infrastructure.mongodb;

import java.time.Instant;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserDocument {

    @Id
    private String id;
    private String username;
    private String passwordHash;
    private String email;
    private List<String> roleIds;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}
