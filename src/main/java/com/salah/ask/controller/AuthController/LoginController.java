package com.salah.ask.controller.AuthController;

import com.salah.ask.controller.request.LoginRequest;
import com.salah.ask.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping
    private ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest)  {
        return ResponseEntity.ok(loginService.login(loginRequest));
    }



}
