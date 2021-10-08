package com.salah.ask.service;

import com.salah.ask.dto.AuthResponse;
import com.salah.ask.dto.LoginRequest;
import com.salah.ask.dto.RegisterRequest;
import com.salah.ask.model.user.Role;
import com.salah.ask.model.user.User;
import com.salah.ask.model.user.UserRoles;
import com.salah.ask.repository.JpaUserRepository;
import com.salah.ask.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RegisterServiceImpTestWithoutSpringBoot {
    @Mock
    JpaUserRepository userRepository;
    @Mock
    LoginService loginService;
    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RegisterServiceImp registerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void register() {
        RegisterRequest registerRequest = new RegisterRequest("salah@", "1234",
                "salah", "rahim");
        Role role = new Role(UserRoles.ROLE_REGULAR);
        when(roleRepository.findByRole(role.getRole())).thenReturn(Optional.of(role));
        when(loginService.login(new LoginRequest(registerRequest.getEmail(), registerRequest.getPassword())))
                .thenReturn(new AuthResponse("aaa"));


    }

}