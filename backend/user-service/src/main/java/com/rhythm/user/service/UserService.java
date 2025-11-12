package com.rhythm.user.service;

import com.rhythm.user.dto.UserCreateDto;
import com.rhythm.user.dto.UserResponseDto;
import com.rhythm.user.exception.BadRequestException;
import com.rhythm.user.exception.ResourceNotFoundException;
import com.rhythm.user.model.Tenant;
import com.rhythm.user.model.User;
import com.rhythm.user.repository.TenantRepository;
import com.rhythm.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;

    @Transactional
    public UserResponseDto create(UserCreateDto dto) {
        Tenant tenant = tenantRepository
                .findById(dto.getTenantId())
                .orElseThrow(() -> new BadRequestException("Tenant with id " + dto.getTenantId() + " not found"));

        userRepository.findByTenantIdAndEmail(dto.getTenantId(), dto.getEmail())
                .ifPresent(user -> {
            throw new BadRequestException("User with email " + dto.getEmail() + " already exists in tenant " + tenant.getName());
        });

        User user = User.builder()
                .tenant(tenant)
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .specialty(dto.getSpecialty())
                .profile("{}")
                .build();
        User savedUser = userRepository.save(user);
        return toDto(savedUser);
    }

    public List<UserResponseDto> listByTenant(UUID tenantId) {
        return userRepository.findByTenantId(tenantId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserResponseDto get(UUID id) {
        return userRepository.findById(id).map(this::toDto).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Transactional
    public UserResponseDto update(UUID id, UserCreateDto dto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        if (dto.getEmail() != null && !dto.getEmail().equals(existingUser.getEmail())) {
            userRepository.findByTenantIdAndEmail(existingUser.getTenant().getId(), dto.getEmail())
                    .ifPresent(user -> {
                throw new BadRequestException("User with email " + dto.getEmail() + " already exists in tenant " + existingUser.getTenant().getName());
            });
            existingUser.setEmail(dto.getEmail());
        }
        if (dto.getName() != null) existingUser.setName(dto.getName());
        if (dto.getPhone() != null) existingUser.setPhone(dto.getPhone());
        if (dto.getSpecialty() != null) existingUser.setSpecialty(dto.getSpecialty());
        existingUser.setRole(dto.getRole());
        User updatedUser = userRepository.save(existingUser);
        return toDto(updatedUser);
    }

    @Transactional
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User not found!");
        userRepository.deleteById(id);
    }


    private UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .tenantId(user.getTenant().getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .specialty(user.getSpecialty())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
