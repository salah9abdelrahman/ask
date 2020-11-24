package com.salah.ask.service;

import com.salah.ask.controller.request.AuthResponse;
import com.salah.ask.controller.request.RegisterRequest;

public interface RegisterService {
    AuthResponse register( RegisterRequest registerRequest);
}
