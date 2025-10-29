package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.exp.banduapp.models.entities.Role;
import org.exp.banduapp.models.enums.RoleName;
import org.exp.banduapp.repository.RoleRepository;
import org.exp.banduapp.service.face.RoleService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrCreateRoleByName(RoleName roleName) {

        if (roleName == null) {
            throw new IllegalArgumentException("Role nomi bo'sh bo'lmasligi kerak");
        }

        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    log.info("Yangi rol yaratilmoqda: {}", roleName);;
                    return createNewRole(roleName);
                });
    }

    private Role createNewRole(RoleName roleName) {
        return roleRepository.save(new Role(roleName));
    }
}
