package com.salah.ask.service;

import com.salah.ask.model.user.Role;
import com.salah.ask.repository.RoleRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles(){
        log.info("get roles");
        return roleRepository.findAll();
    }
}
