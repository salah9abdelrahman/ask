package com.salah.ask.repository;

import com.salah.ask.model.user.Role;
import com.salah.ask.model.user.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(UserRoles role);
}
