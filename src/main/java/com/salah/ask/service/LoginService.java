package com.salah.ask.service;

import com.salah.ask.dto.AuthResponse;
import com.salah.ask.dto.LoginRequest;

public interface LoginService {
    AuthResponse login(LoginRequest loginRequest);
}
