package com.imamachi.simplepolling.repository;

import com.imamachi.simplepolling.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    // xxxRepository#findAll
    // xxxRepository#findById
    // xxxRepository#save
    // xxxRepository#deleteById
    Role findByRole(Role.RoleName admin);
}
