package com.rhythm.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

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

    @NotBlank
    private UUID tenantId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
}
