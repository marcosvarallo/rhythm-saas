package com.rhythm.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Email
    private String email;

    @Size(max = 30)
    private String phone;

    @Size(max = 100)
    private String specialty;

    @NotBlank
    private String role;

    private String profile;
}
