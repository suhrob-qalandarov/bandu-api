package org.exp.banduapp.service.face;

import org.exp.banduapp.models.entities.Role;
import org.exp.banduapp.models.enums.RoleName;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role getOrCreateRoleByName(RoleName roleName);
}
