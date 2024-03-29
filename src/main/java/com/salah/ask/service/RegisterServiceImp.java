package com.salah.ask.service;

import com.salah.ask.dto.AuthResponse;
import com.salah.ask.dto.LoginRequest;
import com.salah.ask.dto.RegisterRequest;
import com.salah.ask.exception.custom.EntityAlreadyExists;
import com.salah.ask.exception.custom.EntityNotFoundException;
import com.salah.ask.model.user.Role;
import com.salah.ask.model.user.User;
import com.salah.ask.model.user.UserRoles;
import com.salah.ask.repository.RoleRepository;
import com.salah.ask.repository.JpaUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImp implements RegisterService {
    private final JpaUserRepository userRepository;
    private final LoginService loginService;
    private final RoleRepository roleRepository;

    public RegisterServiceImp(JpaUserRepository userRepository, LoginService loginService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.loginService = loginService;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        isUserExists(registerRequest);
        Optional<Role> role = roleRepository.findByRole(UserRoles.ROLE_ADMIN);
        role.orElseThrow(() -> new EntityNotFoundException("Role Not Found"));
        System.out.println("role");
        System.out.println(role);
        User user = new User()
                .setActive(false)
                .setEmail(registerRequest.getEmail())
                .setFirstName(registerRequest.getFirstName())
                .setLastName(registerRequest.getLastName())
                .setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()))
                .setIsEnabled(true);

        user.addRole(role.get());
        userRepository.save(user);

        return loginService.login(new LoginRequest(registerRequest.getEmail(), registerRequest.getPassword()));
    }

    private void isUserExists(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EntityAlreadyExists("User already exists");
        }
    }
}
