package com.rhythm.user.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private UUID id;
    private UUID tenantId;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String specialty;
    private OffsetDateTime createdAt;
}
