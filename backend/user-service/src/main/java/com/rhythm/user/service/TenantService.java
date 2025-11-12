package com.rhythm.user.service;

import com.rhythm.user.dto.TenantCreateDto;
import com.rhythm.user.exception.BadRequestException;
import com.rhythm.user.model.Tenant;
import com.rhythm.user.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    @Transactional
    public Tenant create(TenantCreateDto dto) {
        if (dto.getDomain() != null && tenantRepository.findByDomain(dto.getDomain()).isPresent()) {
            throw new BadRequestException("Tenant with domain " + dto.getDomain() + " already exists");
        }

        Tenant tenant = Tenant.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .domain(dto.getDomain())
                .plan("FREE")
                .metadata("{}")
                .build();
        return tenantRepository.save(tenant);
    }

    public List<Tenant> list() {
        return tenantRepository.findAll();
    }

    public Tenant get(UUID id) {
        return tenantRepository.findById(id).orElseThrow(() -> new BadRequestException("Tenant with id " + id + " not found"));
    }
}
