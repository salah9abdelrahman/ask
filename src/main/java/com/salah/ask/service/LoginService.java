package com.salah.ask.service;

import com.salah.ask.controller.request.AuthResponse;
import com.salah.ask.controller.request.LoginRequest;

public interface LoginService {
    AuthResponse login(LoginRequest loginRequest);
}
