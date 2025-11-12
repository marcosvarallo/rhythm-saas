package com.rhythm.user.controller;

import com.rhythm.user.dto.TenantCreateDto;
import com.rhythm.user.model.Tenant;
import com.rhythm.user.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<Tenant> create(@Valid @RequestBody TenantCreateDto dto) {
        Tenant tenant = tenantService.create(dto);
        return ResponseEntity.created(URI.create("/api/tenants/" + tenant.getId())).body(tenant);
    }

    @GetMapping
    public List<Tenant> list() {
        return tenantService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> get(@PathVariable UUID id) {
        return ResponseEntity.ok(tenantService.get(id));
    }
}
