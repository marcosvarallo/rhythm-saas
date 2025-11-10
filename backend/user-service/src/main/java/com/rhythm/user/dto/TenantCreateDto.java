package com.rhythm.user.dto;

import jakarta.validation.constraints.NotBlank;

public class TenantCreateDto {

    @NotBlank
    private String name;
    private String email;
    private String phone;
    private String domain;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
}
