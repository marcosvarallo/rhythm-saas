package com.rhythm.user.controller;

import com.rhythm.user.dto.UserCreateDto;
import com.rhythm.user.dto.UserResponseDto;
import com.rhythm.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/tenants/{tenantId}/users")
    public ResponseEntity<UserResponseDto> create(@PathVariable UUID tenantId, @Valid @RequestBody UserCreateDto dto) {
        dto.setTenantId(tenantId);
        UserResponseDto createdUser = userService.create(dto);
        return ResponseEntity.created(URI.create("/api/users/" + createdUser.getId())).body(createdUser);
    }

    @GetMapping("/tenants/{tenantId}/users")
    public List<UserResponseDto> listByTenant(@PathVariable UUID tenantId) {
        return userService.listByTenant(tenantId);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable UUID id, @Valid @RequestBody UserCreateDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
