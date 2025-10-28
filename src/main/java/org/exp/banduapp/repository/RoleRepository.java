package org.exp.banduapp.repository;

import org.exp.banduapp.models.entities.Role;
import org.exp.banduapp.models.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);

    Set<Role> findAllByNameIn(Set<RoleName> names);
}
