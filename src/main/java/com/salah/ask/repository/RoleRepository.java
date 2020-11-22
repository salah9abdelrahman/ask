package com.salah.ask.repository;

import com.salah.ask.model.user.Role;
import com.salah.ask.model.user.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(UserRoles role);
}
