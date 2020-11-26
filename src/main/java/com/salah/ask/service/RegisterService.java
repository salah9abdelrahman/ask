package com.salah.ask.service;

import com.salah.ask.dto.AuthResponse;
import com.salah.ask.dto.RegisterRequest;

import javax.management.relation.RoleNotFoundException;

public interface RegisterService {
    AuthResponse register( RegisterRequest registerRequest) throws RoleNotFoundException;
}
