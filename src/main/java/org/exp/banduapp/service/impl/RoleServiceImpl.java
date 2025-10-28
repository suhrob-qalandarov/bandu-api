package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.entities.Role;
import org.exp.banduapp.models.enums.RoleName;
import org.exp.banduapp.repository.RoleRepository;
import org.exp.banduapp.service.face.RoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrCreateRoleByName(RoleName roleName) {
        return roleRepository.findByName(RoleName.ROLE_USER)
                .orElse(createNewRole(roleName));
    }

    private Role createNewRole(RoleName roleName) {
        return roleRepository.save(new Role(roleName));
    }
}
