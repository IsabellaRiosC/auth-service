package com.nttdata.authservice.api.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponseDto {

    private String userId;
    private String username;
    private String email;
    private List<String> roles;
}
