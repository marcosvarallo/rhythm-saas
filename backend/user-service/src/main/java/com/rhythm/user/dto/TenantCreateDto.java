package com.rhythm.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantCreateDto {

    @NotBlank
    private String name;
    private String email;
    private String phone;
    private String domain;
}
