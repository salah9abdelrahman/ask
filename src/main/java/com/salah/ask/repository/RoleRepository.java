package com.salah.ask.repository;

import com.salah.ask.model.user.Role;
import com.salah.ask.model.user.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(UserRoles role);
}
