package com.salah.ask.service;

import com.salah.ask.controller.request.AuthResponse;
import com.salah.ask.controller.request.LoginRequest;
import com.salah.ask.controller.request.RegisterRequest;
import com.salah.ask.model.user.Role;
import com.salah.ask.model.user.User;
import com.salah.ask.model.user.UserRoles;
import com.salah.ask.repository.RoleRepository;
import com.salah.ask.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImp implements RegisterService {
    private final UserRepository userRepository;
    private final LoginService loginService;
    private final RoleRepository roleRepository;

    public RegisterServiceImp(UserRepository userRepository, LoginService loginService,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.loginService = loginService;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        Role role = roleRepository.findByRole(UserRoles.ROLE_REGULAR);
        System.out.println("role");
        System.out.println(role);
        User user = new User()
                .setActive(false)
                .setEnabled(true)
                .setEmail(registerRequest.getEmail())
                .setFirstName(registerRequest.getFirstName())
                .setLastName(registerRequest.getLastName())
                .setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));

        user.addRole(role);

        userRepository.save(user);

        System.out.println("user");
        System.out.println(user);

        return loginService.login(new LoginRequest(registerRequest.getEmail(), registerRequest.getPassword()));
    }
}
