package com.salah.ask.service;

import com.salah.ask.controller.request.AuthResponse;
import com.salah.ask.controller.request.RegisterRequest;

import javax.management.relation.RoleNotFoundException;

public interface RegisterService {
    AuthResponse register( RegisterRequest registerRequest) throws RoleNotFoundException;
}
