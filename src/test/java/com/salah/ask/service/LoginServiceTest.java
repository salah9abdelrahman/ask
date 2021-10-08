package com.salah.ask.service;

import com.salah.ask.dto.LoginRequest;
import com.salah.ask.security.UserDetailsServiceImp;
import com.salah.ask.security.jwt.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class LoginServiceTest {
    @Autowired
    LoginService loginService;

    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    UserDetailsServiceImp userDetailsServiceImp;
    @MockBean
    JwtUtil jwtUtil;

    final LoginRequest CORRECT_LOGIN_REQUEST = new LoginRequest("salah", "1234");
    final LoginRequest BAD_LOGIN_REQUEST = new LoginRequest("salah", "1234");

    @Test
    @DisplayName("Test login success")
    void loginSuccess(){
//        doReturn().
        Assertions.assertEquals("alo", "alo");
    }
}
